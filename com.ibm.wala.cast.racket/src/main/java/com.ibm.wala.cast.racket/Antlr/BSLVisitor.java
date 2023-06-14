// Generated from java-escape by ANTLR 4.11.1
package com.ibm.wala.cast.racket.Antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BSLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(BSLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#defOrExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefOrExpr(BSLParser.DefOrExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(BSLParser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(BSLParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#testCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTestCase(BSLParser.TestCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#libraryRequire}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLibraryRequire(BSLParser.LibraryRequireContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#pkg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPkg(BSLParser.PkgContext ctx);
	/**
	 * Visit a parse tree produced by {@link BSLParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(BSLParser.NameContext ctx);
}