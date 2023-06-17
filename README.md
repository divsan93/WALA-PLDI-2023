# WALA Racket

## Module 3

#### Module 3 Solution:

**Antlr2CAstTranslator.java** - Code for Declare Node:
```java
CAstNode declNode = makeNode(context,fFactory, n, CAstNode.DECL_STMT,
fFactory.makeConstant(new CAstSymbolImpl(varName, varType, false)),
fFactory.makeConstant(varValue));

context.addNameDecl(declNode);
return declNode;
```

**Antlr2CAstTranslator.java** - Code for Binary Node:
```java
CAstNode binaryNode = makeNode(context, fFactory, CAstNode.BINARY_EXPR, myOperator, visit(n.expr(0), context), visit(n.expr(1), context), n);
return binaryNode

```

## Module 4

#### Module 4 Solution:

**RacketPrimitiveTypes.java** - Primitive types in Racket:

```java
primNameMap.put("b", new RacketPrimitiveTypeMap("bool", TypeReference.Boolean.getName().toString()));
primNameMap.put("c", new RacketPrimitiveTypeMap("char", TypeReference.Char.getName().toString()));
```
