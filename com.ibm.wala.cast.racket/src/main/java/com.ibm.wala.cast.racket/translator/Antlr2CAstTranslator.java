package com.ibm.wala.cast.racket.translator;
import com.ibm.wala.cast.racket.Antlr.BSLLexer;
import com.ibm.wala.cast.racket.Antlr.BSLParser;
import com.ibm.wala.cast.racket.Antlr.BSLParser.*;
import com.ibm.wala.cast.ir.translator.TranslatorToCAst;
import com.ibm.wala.cast.racket.types.RacketPrimitiveTypes;
import com.ibm.wala.cast.racket.types.RacketTypes;
import com.ibm.wala.cast.tree.*;
import com.ibm.wala.cast.tree.CAstSourcePositionMap.Position;
import com.ibm.wala.cast.tree.impl.*;
import com.ibm.wala.cast.tree.rewrite.CAstRewriter;
import com.ibm.wala.cast.tree.rewrite.CAstRewriterFactory;
import com.ibm.wala.cast.util.CAstPrinter;
import com.ibm.wala.classLoader.ModuleEntry;
import com.ibm.wala.classLoader.SourceFileModule;
import com.ibm.wala.util.collections.EmptyIterator;
import com.ibm.wala.util.collections.HashMapFactory;
import com.ibm.wala.util.collections.Pair;
import com.ibm.wala.util.debug.Assertions;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.lang.Integer;

/*
In this class, we define the mechanism to translate the parse tree
generated using the Antlr4 into a tree formed using CAstEntity. CAstEntity is
a WALA representation, where based on the type of instructions the entity can be different.
For instance, one type of entity can be ClassEntity, which will store all the class related information
alongwith the CAstNode of the members of the class. CAstNode is the WALA presentation of the tree.
 */

public class Antlr2CAstTranslator<T extends Position> implements TranslatorToCAst {
    /* In this part, we define various fields that we will using in the later part.*/
    protected boolean dump = false;
    /* Antlr Parse tree */
    protected ParseTree tree;
    /* CAstImpl class has various type of CAstNode creation implementation that we will using based on the scenario. */
    protected final CAst fFactory;

    protected String fullPath;

    private final ModuleEntry sourceModule;

    public Antlr2CAstTranslator(
            CAst Ast,
            ModuleEntry m,
            String scriptName) throws IOException {
        this.fFactory = Ast;
        this.fullPath = scriptName;
        this.sourceModule = m;
        if(!fullPath.contains("/"))
            fullPath=((SourceFileModule) m).getAbsolutePath();
        ANTLRFileStream input = new ANTLRFileStream(fullPath);

        //PARSE TREE GENERATION CODE GOES HERE...
        BSLLexer lexer = new BSLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BSLParser parser = new BSLParser(tokens);
        this.tree  = parser.program();

        System.out.println(tree.toStringTree(parser));
    }

    public CAstEntity translateToCAst(String filename) {
        CAstEntity racketEntities;
        racketEntities =  visitProgram(tree, new RootContext());

        if (dump) {
            CAstPrinter.printTo(racketEntities, new PrintWriter(System.err));
        }
        return new FileEntity(filename, racketEntities);
    }

    @Override
    public <C extends CAstRewriter.RewriteContext<K>, K extends CAstRewriter.CopyKey<K>> void addRewriter(CAstRewriterFactory<C, K> factory, boolean prepend) {
        assert false;
    }

    @Override
    public CAstEntity translateToCAst() throws Error, IOException {
        CAstEntity racketEntities;

        racketEntities =  visitProgram(tree, new RootContext());

        if (dump) {
            CAstPrinter.printTo(racketEntities, new PrintWriter(System.err));
        }

        return new FileEntity(fullPath.substring(fullPath.lastIndexOf('-') + 1), racketEntities);
    }

    /* DO NOT ENTER: Construction Ahead */
    protected class ReportEntity implements CAstEntity {

        private final String name;

        private final int kind;

        private final Map<CAstNode, Collection<CAstEntity>> subs;

        private final CAstNode ast;

        private final CAstControlFlowMap map;

        private final CAstSourcePositionMap pos;

        private final Position entityPosition;

        private ReportEntity(ParseTree n,
                             Map<CAstNode, Collection<CAstEntity>> subs,
                             CAstNode ast,
                             CAstControlFlowMap map,
                             CAstSourcePositionMap pos,
                             String name) {
            this.name = name;
            this.subs = subs;
            this.ast = ast;
            this.map = map;
            this.pos = pos;
            kind = CAstEntity.SCRIPT_ENTITY;
            this.entityPosition = pos.getPosition(ast);
        }

        @Override
        public int getKind() {
            return kind;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getSignature() {
            return null;
        }

        @Override
        public String[] getArgumentNames() {
            return new String[0];
        }

        @Override
        public CAstNode[] getArgumentDefaults() {
            return new CAstNode[0];
        }

        @Override
        public int getArgumentCount() {
            return 0;
        }

        @Override
        public Map<CAstNode, Collection<CAstEntity>> getAllScopedEntities() {
            return Collections.unmodifiableMap(subs);
        }

        @Override
        public Iterator<CAstEntity> getScopedEntities(CAstNode construct) {
            if (subs.containsKey(construct)) return subs.get(construct).iterator();
            else return EmptyIterator.instance();
        }

        @Override
        public CAstNode getAST() {
            return ast;
        }

        @Override
        public CAstControlFlowMap getControlFlow() {
            return map;
        }

        @Override
        public CAstSourcePositionMap getSourceMap() {
            return pos;
        }

        @Override
        public Position getPosition() {
            return entityPosition;
        }

        @Override
        public Position getNamePosition() {
            return null;
        }

        @Override
        public Position getPosition(int arg) {
            return null;
        }

        @Override
        public CAstNodeTypeMap getNodeTypeMap() {
            return null;
        }

        @Override
        public Collection<CAstQualifier> getQualifiers() {
            Assertions.UNREACHABLE("RangeetsUnnamedCAstEntity$2.getQualifiers()");
            return null;
        }

        @Override
        public CAstType getType() {
            return null;
        }

        @Override
        public Collection<CAstAnnotation> getAnnotations() {
            return null;
        }
    }

    protected static final class FileEntity implements CAstEntity {
        private final String fName;

        private final CAstEntity fTopLevelDecls;

        public FileEntity(
                String fName, CAstEntity topLevelDecls) {
            this.fName = fName;
            fTopLevelDecls = topLevelDecls;
        }

        @Override
        public Collection<CAstAnnotation> getAnnotations() {
            return null;
        }

        @Override
        public int getKind() {
            return FILE_ENTITY;
        }

        @Override
        public String getName() {
            return fName;
        }

        @Override
        public String getSignature() {
            Assertions.UNREACHABLE();
            return null;
        }

        @Override
        public String[] getArgumentNames() {
            return new String[0];
        }

        @Override
        public CAstNode[] getArgumentDefaults() {
            return new CAstNode[0];
        }

        @Override
        public int getArgumentCount() {
            return 0;
        }

        @Override
        public Map<CAstNode, Collection<CAstEntity>> getAllScopedEntities() {
            return Collections.singletonMap(null, Collections.singletonList( fTopLevelDecls ) );
        }

        @Override
        public Iterator<CAstEntity> getScopedEntities(CAstNode construct) {
            Assertions.UNREACHABLE(
                    "CompilationUnitEntity asked for AST-related entities, but it has no AST.");
            return null;
        }

        @Override
        public CAstNode getAST() {
            return null;
        }

        @Override
        public CAstControlFlowMap getControlFlow() {
            Assertions.UNREACHABLE("CompilationUnitEntity.getControlFlow()");
            return null;
        }

        @Override
        public CAstSourcePositionMap getSourceMap() {
            Assertions.UNREACHABLE("CompilationUnitEntity.getSourceMap()");
            return null;
        }

        @Override
        public CAstSourcePositionMap.Position getPosition() {
            return null;
        }

        @Override
        public CAstNodeTypeMap getNodeTypeMap() {
            Assertions.UNREACHABLE("CompilationUnitEntity.getNodeTypeMap()");
            return null;
        }

        @Override
        public Collection<CAstQualifier> getQualifiers() {
            return Collections.emptyList();
        }

        @Override
        public CAstType getType() {
            Assertions.UNREACHABLE("CompilationUnitEntity.getType()");
            return null;
        }

        @Override
        public Position getPosition(int arg) {
            return null;
        }

        @Override
        public Position getNamePosition() {
            return null;
        }
    }


    public CAstEntity visitProgram(ParseTree n, WalkContext context)
    {
        final ReportWalkContext child = new ReportWalkContext(new RootContext(), n);
        final List<CAstNode> stmts;
        stmts = new ArrayList<>(n.getChildCount()-1);
        for(int i=0; i < n.getChildCount()-1; i++) {
            stmts.add(visitdefOrExpr((DefOrExprContext) n.getChild(i), child));
        }
        final CAstNode rast =
                makeNode(
                        context,
                        fFactory,
                        null,
                        CAstNode.LOCAL_SCOPE,
                        makeNode(
                                child, fFactory, n, CAstNode.BLOCK_STMT, stmts));

        final CAstControlFlowMap map = child.cfg();
        final CAstSourcePositionMap pos = child.pos();
        final Map<CAstNode, Collection<CAstEntity>> subs =
                HashMapFactory.make(child.getScopedEntities());
        System.out.println(rast);
        return new ReportEntity(n, subs, rast, map, pos, "dummy");
    }

    public CAstNode visitdefOrExpr(DefOrExprContext n, WalkContext context)
    {
        CAstNode visitChild = visit(n.getChild(0), context);
        final CAstNode stmt = makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visitChild);
        return stmt;
    }

    /**
     * HANDLING OF VARIOUS THINGS
     */
    private CAstNode visit(ParseTree n, WalkContext context) {
        if(n==null) {
            return makeNode(context, fFactory, null, CAstNode.EMPTY);
        } else if (n instanceof DefinitionContext) {
            return visit((DefinitionContext)n, context);
        } else if (n instanceof ExprContext) {
            return visit((ExprContext)n, context);
        } else if (n instanceof TestCaseContext) {
            return visit((TestCaseContext)n, context);
        } else if (n instanceof LibraryRequireContext) {
            return visit((LibraryRequireContext) n, context);
        }

        Assertions.UNREACHABLE("Unhandled node type " + n.getClass().getCanonicalName());

        return null;
    }

    private CAstNode visit(DefinitionContext n, WalkContext context) {
        String varName = n.name(0).getText();
        Object varValue = n.expr().getChild(0).getText();
        CAstType varType = getType(n);
        if(n.expr().getChildCount() == 1) {
            if(varType.getName().equals("I")) {
                varValue = Integer.parseInt((String) varValue);
            }
            // CODE FOR DECLARATION NODE GOES HERE ....

            CAstNode declNode = makeNode(context,fFactory, n, CAstNode.DECL_STMT,
                    fFactory.makeConstant(new CAstSymbolImpl(varName, varType, false)),
                    fFactory.makeConstant(varValue));

            context.addNameDecl(declNode);
            return declNode;
        }
        return null;
    }

    public CAstType getType(DefinitionContext n)
    {
        if(n.expr().BOOLEAN() != null)
            return RacketPrimitiveTypes.lookupType("b");
        else
        {
            if(n.expr().CHARACTER()!= null) {
                return RacketPrimitiveTypes.lookupType("c");
            } else {
                return RacketPrimitiveTypes.lookupType("i");
            }
        }
    }

    private CAstNode visit(ExprContext n, WalkContext context) {
        String myText = n.getText();
        if(n.expr().size() == 3 && myText.substring(0,3).equals("(if")) {
            // if expression..

            return null;
        } else if(n.expr().size() == 2) {
            // binary expression...
            String exprSymbol = n.name().getText();
            CAstOperator myOperator = CAstOperator.OP_ADD;
            switch (exprSymbol) {
                case "-":
                    myOperator = CAstOperator.OP_SUB;
                    break;
                case "*":
                    myOperator = CAstOperator.OP_MUL;
                    break;
                case "/":
                    myOperator = CAstOperator.OP_DIV;
                    break;
                case "=":
                    myOperator = CAstOperator.OP_EQ;
                    break;
            }

            // CODE  FOR BINARY NODE GOES HERE...
            CAstNode binaryNode = makeNode(context, fFactory, CAstNode.BINARY_EXPR, myOperator,
                    visit(n.expr(0), context), visit(n.expr(1), context), n);
            return binaryNode;

        } else if (n.expr().size() == 0) {
            CAstNode varNode = null;
            for (CAstNode c : context.getNameDecls()) {
                if (c.getKind() == CAstNode.DECL_STMT) {
                    CAstSymbolImpl val = (CAstSymbolImpl) c.getChildren().get(0).getValue();
                    if (val.name().equals(n.name().getText()))
                        varNode= makeNode(
                                context,
                                fFactory,
                                n,
                                CAstNode.VAR,
                                fFactory.makeConstant(n.name().getText()),
                                fFactory.makeConstant(val.type()));
                }
            }
            return varNode;
        }
        return null;
    }

    private CAstNode visit(TestCaseContext n, WalkContext context) {
        return null;
    }

    private CAstNode visit(LibraryRequireContext n, WalkContext context) {
        return null;
    }

    protected CAstNode makeNode(WalkContext wc, CAst Ast, ParseTree n, int kind) {
        CAstNode cn = Ast.makeNode(kind);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(WalkContext wc, CAst Ast, ParseTree n, int kind, List<CAstNode> c) {
        CAstNode cn = Ast.makeNode(kind, c);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(WalkContext wc, CAst Ast, T pos, int kind, List<CAstNode> c) {
        CAstNode cn = Ast.makeNode(kind, c);
        wc.pos().setPosition(cn, pos);
        return cn;
    }

    protected CAstNode makeNode(WalkContext wc, CAst Ast, ParseTree n, int kind, CAstNode c1, List<CAstNode> c) {
        c.add(0,c1);
        CAstNode cn = Ast.makeNode(kind, c);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(
            WalkContext wc, CAst Ast, ParseTree n, int kind, CAstNode c1, CAstNode c2) {
        CAstNode cn = Ast.makeNode(kind, c1, c2);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(WalkContext wc, CAst Ast, ParseTree n, int kind, CAstNode c) {
        CAstNode cn = Ast.makeNode(kind, c);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(
            WalkContext wc, CAst Ast, ParseTree n, int kind, CAstNode c1, CAstNode c2, CAstNode c3) {
        CAstNode cn = Ast.makeNode(kind, c1, c2, c3);
        setPos(wc, cn, n);
        return cn;
    }

    protected CAstNode makeNode(
            WalkContext wc,
            CAst Ast,
            ParseTree n,
            int kind,
            CAstNode c1,
            CAstNode c2,
            CAstNode c3,
            CAstNode c4) {
        CAstNode cn = Ast.makeNode(kind, c1, c2, c3, c4);
        setPos(wc, cn, n);
        return cn;
    }
    protected CAstNode makeNode(
            WalkContext wc, CAst Ast, int kind, CAstNode c1, CAstNode c2, CAstNode c3, ParseTree n) {
        CAstNode cn = Ast.makeNode(kind, c1, c2, c3);
        setPos(wc, cn, n);
        //wc.pos().setPosition(wc, cn, pos);
        return cn;
    }
    protected CAstNode makeNode(
            WalkContext wc, CAst Ast, T pos, int kind, CAstNode c1, CAstNode c2, CAstNode c3) {
        CAstNode cn = Ast.makeNode(kind, c1, c2, c3);
        wc.pos().setPosition(cn, pos);
        return cn;
    }

    protected void setPos(WalkContext wc, CAstNode cn, ParseTree ptree) {
        if (ptree != null) wc.pos().setPosition(cn, makePosition(ptree));
    }
    //TODO: Is it line number of column number? If not, add here.
    //Check what is happening in actual life not in IDE life :D.
    public T makePosition(ParseTree n) {
        if(n instanceof TerminalNode)
            return makePosition(((TerminalNodeImpl) n).symbol.getLine(), ((TerminalNodeImpl) n).symbol.getStartIndex(), ((TerminalNodeImpl) n).symbol.getStopIndex());
        return makePosition(((ParserRuleContext)n).getStart().getLine(), ((ParserRuleContext)n).getStart().getStartIndex(), ((ParserRuleContext)n).getStop().getStopIndex());
    }

    public T makePosition(int line, int start, int end) {
        try {
            return (T) new RangePosition(
                    new URL("file://" + fullPath),
                    line,
                    start,
                    end);
        } catch (MalformedURLException e) {
            throw new RuntimeException("bad file: " + fullPath, e);
        }
    }

    public interface WalkContext extends TranslatorToCAst.WalkContext<WalkContext, ParseTree> {
        Collection<Pair<CAstType, Object>> getCatchTargets(CAstType type);

        Map<ParseTree, String> getLabelMap();

        boolean needLValue();
    }

    public static class RootContext extends TranslatorToCAst.RootContext<WalkContext, ParseTree>
            implements WalkContext {

        private final Vector<CAstNode> initializers = new Vector<>();

        @Override
        public Collection<Pair<CAstType, Object>> getCatchTargets(CAstType type) {
            Assertions.UNREACHABLE("RootContext.getCatchTargets()");
            return null;
        }

        @Override
        public Map<ParseTree, String> getLabelMap() {
            Assertions.UNREACHABLE("RootContext.getLabelMap()");
            return null;
        }

        @Override
        public boolean needLValue() {
            Assertions.UNREACHABLE("Rootcontext.needLValue()");
            return false;
        }
        @Override
        public void addNameDecl(CAstNode v) {
            initializers.add(v);
        }

        @Override
        public List<CAstNode> getNameDecls() {
            return initializers;
        }
    }

    public static class     ReportWalkContext extends TranslatorToCAst.FunctionContext<WalkContext, ParseTree> implements WalkContext  {
        public ReportWalkContext(WalkContext parent, ParseTree s) {
            super(parent, s);
        }

        @Override
        public void addNameDecl(CAstNode n) {
            super.addNameDecl(n);
        }

        @Override
        public List<CAstNode> getNameDecls() {
            return super.getNameDecls();
        }

        @Override
        public CAstNode getCatchTarget() {
            return super.getCatchTarget();
        }

        @Override
        public CAstNode getCatchTarget(String s) {
            return super.getCatchTarget(s);
        }

        @Override
        public CAstNodeTypeMapRecorder getNodeTypeMap() {
            return super.getNodeTypeMap();
        }

        @Override
        public ParseTree getContinueFor(String label) {
            return super.getContinueFor(label);
        }

        @Override
        public ParseTree getBreakFor(String label) {
            return super.getBreakFor(label);
        }

        @Override
        public Collection<Pair<CAstType, Object>> getCatchTargets(CAstType type) {
            return null;
        }

        @Override
        public Map<ParseTree, String> getLabelMap() {
            return null;
        }

        @Override
        public boolean needLValue() {
            return false;
        }
    }


}
