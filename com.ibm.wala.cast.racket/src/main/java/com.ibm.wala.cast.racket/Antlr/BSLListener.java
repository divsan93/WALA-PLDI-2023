// Generated from java-escape by ANTLR 4.11.1
package com.ibm.wala.cast.racket.Antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BSLParser}.
 */
public interface BSLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BSLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BSLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BSLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#defOrExpr}.
	 * @param ctx the parse tree
	 */
	void enterDefOrExpr(BSLParser.DefOrExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#defOrExpr}.
	 * @param ctx the parse tree
	 */
	void exitDefOrExpr(BSLParser.DefOrExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterDefinition(BSLParser.DefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitDefinition(BSLParser.DefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(BSLParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(BSLParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#testCase}.
	 * @param ctx the parse tree
	 */
	void enterTestCase(BSLParser.TestCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#testCase}.
	 * @param ctx the parse tree
	 */
	void exitTestCase(BSLParser.TestCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#libraryRequire}.
	 * @param ctx the parse tree
	 */
	void enterLibraryRequire(BSLParser.LibraryRequireContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#libraryRequire}.
	 * @param ctx the parse tree
	 */
	void exitLibraryRequire(BSLParser.LibraryRequireContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#pkg}.
	 * @param ctx the parse tree
	 */
	void enterPkg(BSLParser.PkgContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#pkg}.
	 * @param ctx the parse tree
	 */
	void exitPkg(BSLParser.PkgContext ctx);
	/**
	 * Enter a parse tree produced by {@link BSLParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(BSLParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link BSLParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(BSLParser.NameContext ctx);
}