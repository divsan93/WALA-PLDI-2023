package com.ibm.wala.cast.racket.ipa.callgraph;



import com.ibm.wala.cast.ipa.callgraph.CAstCallGraphUtil;
import com.ibm.wala.cast.ipa.callgraph.StandardFunctionTargetSelector;
import com.ibm.wala.cast.ir.translator.AstTranslator;
import com.ibm.wala.cast.ir.translator.TranslatorToCAst;
import com.ibm.wala.cast.loader.AstMethod;
import com.ibm.wala.cast.racket.loader.RacketLoader;
import com.ibm.wala.cast.racket.loader.RacketLoaderFactory;
import com.ibm.wala.cast.racket.translator.CAstRacketTranslator;
import com.ibm.wala.cast.racket.translator.RacketAstTranslator;
import com.ibm.wala.cast.tree.CAstEntity;
import com.ibm.wala.cast.tree.CAstNode;
import com.ibm.wala.cast.tree.impl.CAstImpl;
import com.ibm.wala.cast.tree.rewrite.CAstRewriterFactory;
import com.ibm.wala.cast.tree.visit.CAstVisitor;
import com.ibm.wala.cast.types.AstMethodReference;
import com.ibm.wala.cast.util.CAstPrinter;
import com.ibm.wala.cfg.AbstractCFG;
import com.ibm.wala.cfg.IBasicBlock;
import com.ibm.wala.classLoader.*;
import com.ibm.wala.classLoader.Module;
import com.ibm.wala.ipa.callgraph.AnalysisScope;
import com.ibm.wala.ipa.callgraph.CGNode;
import com.ibm.wala.ipa.callgraph.CallGraph;
import com.ibm.wala.ipa.callgraph.Entrypoint;
import com.ibm.wala.ipa.callgraph.impl.ClassHierarchyClassTargetSelector;
import com.ibm.wala.ipa.callgraph.impl.ClassHierarchyMethodTargetSelector;
import com.ibm.wala.ipa.cha.ClassHierarchyException;
import com.ibm.wala.ipa.cha.ClassHierarchyFactory;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.types.MethodReference;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.collections.NonNullSingletonIterator;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.*;

public class RacketCallGraphUtil extends com.ibm.wala.cast.ipa.callgraph.CAstCallGraphUtil{
    private static final boolean DEBUG = false;

    /**
     * the translator factory to be used for analysis TODO: pass the factory where needed instead of
     * using a global?
     */
    public static CAstRacketTranslator translatorFactory;

    /**
     * Set up the translator factory. This method should be called before invoking {@link
     * #makeLoaders()}.
     */
    public static void setTranslatorFactory(CAstRacketTranslator translatorFactory) {
        RacketCallGraphUtil.translatorFactory = translatorFactory;
    }

    public static CAstRacketTranslator getTranslatorFactory() {
        return translatorFactory;
    }

    /**
     * @param preprocessor CAst rewriter to use for preprocessing JavaScript source files; may be null
     */
    public static RacketLoaderFactory makeLoaders(CAstRewriterFactory<?, ?> preprocessor) {
        if (translatorFactory == null) {
            throw new IllegalStateException(
                    "com.ibm.wala.cast.js.ipa.callgraph.Util.setTranslatorFactory() must be invoked before makeLoaders()");
        }
        return new RacketLoaderFactory(translatorFactory, preprocessor);
    }

    public static RacketLoaderFactory makeLoaders() {
        return makeLoaders(null);
    }

    public static IClassHierarchy makeHierarchyForScripts(String... scriptFiles)
            throws ClassHierarchyException {
        RacketLoaderFactory loaders = makeLoaders();
        AnalysisScope scope = CAstCallGraphUtil.makeScope(scriptFiles, loaders, RacketLoader.Racket);
        return makeHierarchy(scope, loaders);
    }

    public static IClassHierarchy makeHierarchy(AnalysisScope scope, ClassLoaderFactory loaders)
            throws ClassHierarchyException {
        return ClassHierarchyFactory.make(scope, loaders, RacketLoader.Racket);
    }


    /** @return The set of class names that where defined in the CHA as a result loading process. */
    public static Set<String> loadAdditionalFile(IClassHierarchy cha, RacketLoader cl, URL url)
            throws IOException {
        return loadAdditionalFile(cha, cl, new SourceURLModule(url));
    }

    public static Set<String> loadAdditionalFile(
            IClassHierarchy cha, RacketLoader cl, ModuleEntry M) throws IOException {
        try {
            TranslatorToCAst toCAst = getTranslatorFactory().make(new CAstImpl(), M);
            final Set<String> names = new HashSet<>();
            AstTranslator toIR =
                    new RacketAstTranslator(cl) {
                        @Override
                        protected void defineFunction(
                                CAstEntity N,
                                AstTranslator.WalkContext definingContext,
                                AbstractCFG<SSAInstruction, ? extends IBasicBlock<SSAInstruction>> cfg,
                                SymbolTable symtab,
                                boolean hasCatchBlock,
                                Map<IBasicBlock<SSAInstruction>, TypeReference[]> caughtTypes,
                                boolean hasMonitorOp,
                                AstTranslator.AstLexicalInformation LI,
                                AstMethod.DebuggingInformation debugInfo) {
                            String fnName = 'L' + composeEntityName(definingContext, N);
                            names.add(fnName);
                            super.defineFunction(
                                    N,
                                    definingContext,
                                    cfg,
                                    symtab,
                                    hasCatchBlock,
                                    caughtTypes,
                                    hasMonitorOp,
                                    LI,
                                    debugInfo);
                        }

                        @Override
                        protected void leaveFunctionStmt(
                                CAstNode n, AstTranslator.WalkContext c, CAstVisitor<AstTranslator.WalkContext> visitor) {
                            CAstEntity fn = (CAstEntity) n.getChild(0).getValue();
                            AstTranslator.Scope cs = c.currentScope();
                            if (!cs.contains(fn.getName())
                                    || cs.lookup(fn.getName()).getDefiningScope().getEntity().getKind()
                                    == CAstEntity.SCRIPT_ENTITY) {
                                int result = processFunctionExpr(n, c);
                                assignValue(n, c, cs.lookup(fn.getName()), fn.getName(), result);
                            } else {
                                super.leaveFunctionStmt(n, c, visitor);
                            }
                        }
                    };
            CAstEntity tree;
            try {
                tree = toCAst.translateToCAst();
                if (DEBUG) {
                    CAstPrinter.printTo(tree, new PrintWriter(System.err));
                }
                toIR.translate(tree, M);
                for (String name : names) {
                    IClass fcls = cl.lookupClass(name, cha);
                    cha.addClass(fcls);
                }
                return names;
            } catch (TranslatorToCAst.Error e) {
                return Collections.emptySet();
            }
        } catch (RuntimeException e) {
            return Collections.emptySet();
        }
    }

    public static String simulateToStringForPropertyNames(Object v) {
        if (v instanceof String) {
            return (String) v;
        } else if (v instanceof Double) {
            String result = v.toString();
            if ((Math.round((Double) v)) == (Double) v) {
                result = Long.toString(Math.round((Double) v));
            }
            return result;
        } else if (v instanceof Boolean) {
            if ((Boolean) v) {
                return "true";
            } else {
                return "false";
            }
        } else {
            return null;
        }
    }

    public static class Bootstrap implements SourceModule {
        private final String name;
        private final InputStream stream;
        private final URL url;

        public Bootstrap(String name, InputStream stream, URL url) {
            this.name = name;
            this.stream = stream;
            this.url = url;
        }

        @Override
        public Iterator<? extends ModuleEntry> getEntries() {
            return new NonNullSingletonIterator<>(this);
        }

        @Override
        public boolean isClassFile() {
            return false;
        }

        @Override
        public boolean isSourceFile() {
            return true;
        }

        @Override
        public InputStream getInputStream() {
            return stream;
        }

        @Override
        public boolean isModuleFile() {
            return false;
        }

        @Override
        public Module asModule() {
            return this;
        }

        @Override
        public String getClassName() {
            return getName();
        }

        @Override
        public Module getContainer() {
            return null;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Reader getInputReader() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public URL getURL() {
            return url;
        }
    }

    @SuppressWarnings("resource")
    public static Module getPrologueFile(final String name) {
        return new Bootstrap(name,
                RacketCallGraphUtil.class.getClassLoader().getResourceAsStream(name),
                RacketCallGraphUtil.class.getClassLoader().getResource(name));
    }

}
