# WALA Racket

## Module 2

#### Module 2 Solution:

Code to generate parse tree:

```java
BSLLexer lexer = new BSLLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
BSLParser parser = new BSLParser(tokens);
this.tree  = parser.program();
```
## Module 3

#### Module 3 Solution:

Code for Declare Node:
```java
CAstNode declNode = makeNode(context,fFactory, n, CAstNode.DECL_STMT,
fFactory.makeConstant(new CAstSymbolImpl(varName, varType, false)),
fFactory.makeConstant(varValue));

context.addNameDecl(declNode);
return declNode;
```

Code for Binary Node:
```java
CAstNode binaryNode = makeNode(context, fFactory, CAstNode.BINARY_EXPR, myOperator, visit(n.expr(0), context), visit(n.expr(1), context), n);
return binaryNode

```
