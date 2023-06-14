package com.ibm.wala.cast.racket.translator;

import com.ibm.wala.cast.ir.translator.AstTranslator;
import com.ibm.wala.cast.loader.AstMethod;
import com.ibm.wala.cast.racket.loader.RacketLoader;
import com.ibm.wala.cast.tree.CAstEntity;
import com.ibm.wala.cast.tree.CAstNode;
import com.ibm.wala.cast.tree.CAstSourcePositionMap;
import com.ibm.wala.cast.tree.CAstType;
import com.ibm.wala.cfg.AbstractCFG;
import com.ibm.wala.cfg.IBasicBlock;
import com.ibm.wala.ssa.SSAInstruction;
import com.ibm.wala.ssa.SymbolTable;
import com.ibm.wala.types.TypeName;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.debug.Assertions;

import java.util.Map;

public class RacketAstTranslator extends AstTranslator {
    private static final boolean DEBUG = false;


    public RacketAstTranslator(RacketLoader loader) {
        super(loader);
    }
    @Override
    protected boolean useDefaultInitValues() {
        return false;
    }

    @Override
    protected boolean treatGlobalsAsLexicallyScoped() {
        return false;
    }

    @Override
    protected TypeReference defaultCatchType() {
        return null;
    }

    @Override
    protected TypeReference makeType(CAstType type) {
        return TypeReference.findOrCreate(
                        loader.getReference(), TypeName.string2TypeName(type.getName()));
    }

    @Override
    protected boolean defineType(CAstEntity type, WalkContext wc) {
        return false;
    }

    @Override
    protected String composeEntityName(WalkContext parent, CAstEntity f) {
        if (f.getKind() == CAstEntity.SCRIPT_ENTITY) return f.getName();
        else return parent.getName() + '/' + f.getName();
    }

    @Override
    protected void declareFunction(CAstEntity N, WalkContext context) {
        String fnName = composeEntityName(context, N);
        if (N.getKind() == CAstEntity.SCRIPT_ENTITY) {
            ((RacketLoader) loader).defineScriptType('L' + fnName, N.getPosition(), N, context);
        } else if (N.getKind() == CAstEntity.FUNCTION_ENTITY) {
            ((RacketLoader) loader).defineFunctionType('L' + fnName, N.getPosition(), N, context);
        } else {
            Assertions.UNREACHABLE();
        }
    }

    @Override
    protected void defineFunction(
            CAstEntity N,
            WalkContext definingContext,
            AbstractCFG<SSAInstruction, ? extends IBasicBlock<SSAInstruction>> cfg,
            SymbolTable symtab,
            boolean hasCatchBlock,
            Map<IBasicBlock<SSAInstruction>, TypeReference[]> caughtTypes,
            boolean hasMonitorOp,
            AstLexicalInformation LI,
            AstMethod.DebuggingInformation debugInfo) {
        if (DEBUG) System.err.println(("\n\nAdding code for " + N));
        String fnName = composeEntityName(definingContext, N);

        if (DEBUG) System.err.println(cfg);

        ((RacketLoader) loader)
                .defineCodeBodyCode(
                        'L' + fnName, cfg, symtab, hasCatchBlock, caughtTypes, hasMonitorOp, LI, debugInfo);
    }

    @Override
    protected void defineField(CAstEntity topEntity, WalkContext context, CAstEntity fieldEntity) {

    }


    @Override
    protected void doThrow(WalkContext context, int exception) {

    }

    @Override
    public void doArrayRead(WalkContext context, int result, int arrayValue, CAstNode arrayRef, int[] dimValues) {

    }

    @Override
    public void doArrayWrite(WalkContext context, int arrayValue, CAstNode arrayRef, int[] dimValues, int rval) {

    }

    @Override
    protected void doFieldRead(WalkContext context, int result, int receiver, CAstNode elt, CAstNode parent) {

    }

    @Override
    protected void doFieldWrite(WalkContext context, int receiver, CAstNode elt, CAstNode parent, int rval) {

    }

    @Override
    protected void doMaterializeFunction(CAstNode node, WalkContext context, int result, int exception, CAstEntity fn) {

    }

    @Override
    protected void doNewObject(WalkContext context, CAstNode newNode, int result, Object type, int[] arguments) {

    }

    @Override
    protected void doCall(WalkContext context, CAstNode call, int result, int exception, CAstNode name, int receiver, int[] arguments) {

    }

    @Override
    protected CAstType topType() {
        return null;
    }

    @Override
    protected CAstType exceptionType() {
        return null;
    }

    @Override
    protected void doPrimitive(int resultVal, WalkContext context, CAstNode primitiveCall) {

    }

    @Override
    protected CAstSourcePositionMap.Position[] getParameterPositions(CAstEntity e) {
        return new CAstSourcePositionMap.Position[0];
    }
}