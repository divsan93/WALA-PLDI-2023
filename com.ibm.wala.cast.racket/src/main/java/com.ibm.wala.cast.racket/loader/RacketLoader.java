package com.ibm.wala.cast.racket.loader;

import com.ibm.wala.analysis.typeInference.PrimitiveType;
import com.ibm.wala.cast.ir.translator.AstTranslator;
import com.ibm.wala.cast.ir.translator.TranslatorToCAst;
import com.ibm.wala.cast.ir.translator.TranslatorToIR;
import com.ibm.wala.cast.loader.AstMethod;
import com.ibm.wala.cast.loader.CAstAbstractModuleLoader;
import com.ibm.wala.cast.racket.translator.CAstRacketTranslator;
import com.ibm.wala.cast.racket.translator.RacketAstTranslator;
import com.ibm.wala.cast.racket.types.RacketTypes;
import com.ibm.wala.cast.tree.CAst;
import com.ibm.wala.cast.tree.CAstEntity;
import com.ibm.wala.cast.tree.CAstQualifier;
import com.ibm.wala.cast.tree.CAstSourcePositionMap;
import com.ibm.wala.cast.tree.rewrite.CAstRewriterFactory;
import com.ibm.wala.cfg.AbstractCFG;
import com.ibm.wala.cfg.IBasicBlock;
import com.ibm.wala.classLoader.*;
import com.ibm.wala.classLoader.Module;
import com.ibm.wala.core.util.strings.Atom;
import com.ibm.wala.ipa.callgraph.AnalysisOptions;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.IAnalysisCacheView;
import com.ibm.wala.ipa.callgraph.impl.AbstractRootMethod;
import com.ibm.wala.ipa.callgraph.propagation.InstanceKey;
import com.ibm.wala.ipa.callgraph.propagation.PointerAnalysis;
import com.ibm.wala.ipa.callgraph.propagation.PointerKey;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ipa.modref.ExtendedHeapModel;
import com.ibm.wala.ipa.modref.ModRef;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SSAInstructionFactory;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.collections.HashMapFactory;
import com.ibm.wala.util.collections.HashSetFactory;
import com.ibm.wala.util.collections.Pair;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RacketLoader extends CAstAbstractModuleLoader {
    public Map<CAstEntity, IClass> fTypeMap = HashMapFactory.make();
    protected final Map<TypeName, IClass> loadedClasses = HashMapFactory.make();
    private final CAstRacketTranslator translatorFactory;

    private final CAstRewriterFactory<?, ?> preprocessor;

    public RacketLoader(IClassHierarchy cha, CAstRacketTranslator translatorFactory) {
        this(cha, translatorFactory, null);
    }

    public RacketLoader(
            IClassHierarchy cha,
            CAstRacketTranslator translatorFactory,
            CAstRewriterFactory<?, ?> preprocessor) {
        super(cha);
        this.translatorFactory = translatorFactory;
        this.preprocessor = preprocessor;
    }
    public static final Language Racket =
            new LanguageImpl() {
                @Override
                public Atom getName() {
                    return Atom.findOrCreateUnicodeAtom("Racket");
                }

                @Override
                public TypeReference getRootType() {
                    return RacketTypes.Root;
                }

                @Override
                public TypeReference getThrowableType() {
                    return null;
                }

                @Override
                public TypeReference getConstantType(Object o) {
                    if (o == null) {
                        return TypeReference.Null;
                    } else {
                        Class<?> c = o.getClass();
                        if (c == Boolean.class) {
                            return TypeReference.Boolean;
                        } else if (c == String.class) {
                            return TypeReference.JavaLangString;
                        } else if (c == Integer.class) {
                            return TypeReference.Int;
                        } else if (c == Float.class) {
                            return TypeReference.Float;
                        } else if (c == Double.class) {
                            return TypeReference.Double;
                        } else {
                            assert false : "cannot determine type for " + o + " of class " + c;
                            return null;
                        }
                    }
                }

                @Override
                public boolean isNullType(TypeReference t) {
                    return (t==TypeReference.Null);
                }

                @Override
                public boolean isIntType(TypeReference t) {
                    return (t==TypeReference.Int);
                }

                @Override
                public boolean isLongType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isVoidType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isFloatType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isDoubleType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isStringType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isMetadataType(TypeReference t) {
                    return false;
                }

                @Override
                public boolean isCharType(TypeReference t) {
                    return (t==TypeReference.Char);
                }

                @Override
                public boolean isBooleanType(TypeReference t) {
                    return (t==TypeReference.Boolean);
                }

                @Override
                public Object getMetadataToken(Object value) {
                    return null;
                }

                @Override
                public TypeReference[] getArrayInterfaces() {
                    return new TypeReference[0];
                }

                @Override
                public TypeName lookupPrimitiveType(String name) {
                    return TypeName.findOrCreate(name);
                }

                @Override
                public SSAInstructionFactory instructionFactory() {
                    return null;
                }

                @Override
                public Collection<TypeReference> inferInvokeExceptions(MethodReference target, IClassHierarchy cha) {
                    return null;
                }

                @Override
                public TypeReference getStringType() {
                    return null;
                }

                @Override
                public TypeReference getPointerType(TypeReference pointee) {
                    return null;
                }

                @Override
                public PrimitiveType getPrimitive(TypeReference reference) {
                    return  PrimitiveType.getPrimitive(reference);
                }

                @Override
                public boolean methodsHaveDeclaredParameterTypes() {
                    return false;
                }

                @Override
                public AbstractRootMethod getFakeRootMethod(IClassHierarchy cha, AnalysisOptions options, IAnalysisCacheView cache) {
                    return null;
                }

                @Override
                public <T extends InstanceKey> ModRef.RefVisitor<T, ? extends ExtendedHeapModel> makeRefVisitor(CGNode n, Collection<PointerKey> result, PointerAnalysis<T> pa, ExtendedHeapModel h) {
                    return null;
                }

                @Override
                public <T extends InstanceKey> ModRef.ModVisitor<T, ? extends ExtendedHeapModel> makeModVisitor(CGNode n, Collection<PointerKey> result, PointerAnalysis<T> pa, ExtendedHeapModel h, boolean ignoreAllocHeapDefs) {
                    return null;
                }
            };
    @Override
    protected TranslatorToCAst getTranslatorToCAst(CAst ast, ModuleEntry M, List<Module> modules) throws IOException {
        TranslatorToCAst translator = translatorFactory.make(ast, M);
        if (preprocessor != null) translator.addRewriter(preprocessor, true);
        return translator;
    }

    @Override
    protected boolean shouldTranslate(CAstEntity entity) {
        return true;
    }

    @Override
    protected TranslatorToIR initTranslator(Set<Pair<CAstEntity, ModuleEntry>> topLevelEntities) {
        return new RacketAstTranslator(this);
    }

    @Override
    public ClassLoaderReference getReference() {
        return RacketTypes.racketLoader;
    }

    @Override
    public Language getLanguage() {
        return Racket;
    }

    @Override
    public SSAInstructionFactory getInstructionFactory() {
        return Language.JAVA.instructionFactory();
    }
    public IClass makeCodeBodyType(
            String name,
            TypeReference P,
            CAstSourcePositionMap.Position sourcePosition,
            CAstEntity entity,
            AstTranslator.WalkContext context) {
        return new DynamicCodeBody(
                TypeReference.findOrCreate(RacketTypes.racketLoader, TypeName.string2TypeName(name)),
                P,
                this,
                sourcePosition,
                entity,
                context);
    }

    public IClass defineFunctionType(
            String name, CAstSourcePositionMap.Position pos, CAstEntity entity, AstTranslator.WalkContext context) {
        return makeCodeBodyType(name, RacketTypes.Function, pos, entity, context);
    }

    public IClass defineScriptType(
            String name, CAstSourcePositionMap.Position pos, CAstEntity entity, AstTranslator.WalkContext context) {
        return makeCodeBodyType(name, RacketTypes.Script, pos, entity, context);
    }

    public IMethod defineCodeBodyCode(
            String clsName,
            AbstractCFG<?, ?> cfg,
            SymbolTable symtab,
            boolean hasCatchBlock,
            Map<IBasicBlock<SSAInstruction>, TypeReference[]> caughtTypes,
            boolean hasMonitorOp,
            AstTranslator.AstLexicalInformation lexicalInfo,
            AstMethod.DebuggingInformation debugInfo) {
        DynamicCodeBody C = (DynamicCodeBody) lookupClass(clsName, cha);
        assert C != null : clsName;
        return C.setCodeBody(
                makeCodeBodyCode(
                        cfg, symtab, hasCatchBlock, caughtTypes, hasMonitorOp, lexicalInfo, debugInfo, C));
    }
    private static final Set<CAstQualifier> functionQualifiers;

    static {
        functionQualifiers = HashSetFactory.make();
        functionQualifiers.add(CAstQualifier.PUBLIC);
        functionQualifiers.add(CAstQualifier.FINAL);
    }
    public DynamicMethodObject makeCodeBodyCode(
            AbstractCFG<?, ?> cfg,
            SymbolTable symtab,
            boolean hasCatchBlock,
            Map<IBasicBlock<SSAInstruction>, TypeReference[]> caughtTypes,
            boolean hasMonitorOp,
            AstTranslator.AstLexicalInformation lexicalInfo,
            AstMethod.DebuggingInformation debugInfo,
            IClass C) {
        return new DynamicMethodObject(
                C,
                functionQualifiers,
                cfg,
                symtab,
                hasCatchBlock,
                caughtTypes,
                hasMonitorOp,
                lexicalInfo,
                debugInfo);
    }
    final CoreClass ROOT = new CoreClass(RacketTypes.Root.getName(), null, this, null);
    final CoreClass BOOLEAN =
            new CoreClass(RacketTypes.BOOLEAN.getName(), RacketTypes.Root.getName(), this, null);
    final CoreClass NULL =
            new CoreClass(RacketTypes.Null.getName(), RacketTypes.Root.getName(), this, null);
}
