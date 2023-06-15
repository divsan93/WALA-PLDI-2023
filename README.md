# WALA_Racket
## Module 1
```
java -jar antlr-4.11.1-complete.jar BSL.g4
javac -cp ./antlr-4.11.1-complete.jar *.java

```
```
java -jar antlr-4.11.1-complete.jar -Dlanguage=Java BSL.g4 -visitor
javac -classpath antlr-4.11.1-complete.jar *.java
```
```java
BSLLexer lexer = new BSLLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
BSLParser parser = new BSLParser(tokens);
this.tree  = parser.program();

```
```java
package com.ibm.wala.cast.racket.Antlr;
```
## Module 2
```java
BSLLexer lexer = new BSLLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
BSLParser parser = new BSLParser(tokens);
this.tree  = parser.program();
```
## Module 3
```java
CAstNode declNode = makeNode(context,fFactory, n, CAstNode.DECL_STMT,
fFactory.makeConstant(new CAstSymbolImpl(varName, varType, false)),
fFactory.makeConstant(varValue));

context.addNameDecl(declNode);
return declNode;


CAstNode binaryNode = makeNode(context, fFactory, CAstNode.BINARY_EXPR, myOperator, visit(n.expr(0), context), visit(n.expr(1), context), n);
return binaryNode

```
## Module 4
```java
primNameMap.put("b", new RacketPrimitiveTypeMap("bool", TypeReference.Boolean.getName().toString()));
primNameMap.put("c", new RacketPrimitiveTypeMap("char", TypeReference.Char.getName().toString()));
```