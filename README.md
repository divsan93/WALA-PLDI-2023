# WALA Racket

## Module 1

#### Links:

Download IntelliJ IDEA - https://www.jetbrains.com/idea/download/#section=mac

Versions - https://www.jetbrains.com/idea/download/other.html

Racket Grammar - https://github.com/antlr/grammars-v4/tree/master/racket-bsl

Racket Docs - https://docs.racket-lang.org/index.html

WALA repository - https://github.com/wala/WALA


#### Module 1 Solution:

Commands to generate Parser, Lexer and Visitor:

```java
java -jar antlr-4.11.1-complete.jar -Dlanguage=Java BSL.g4 -visitor
javac -classpath antlr-4.11.1-complete.jar *.java
```

Adding package name to newly generated files:

```java
package com.ibm.wala.cast.racket.Antlr;
```

## Module 2

#### Module 2 Solution:

**Antlr2CAstTranslator.java** - Code to generate parse tree:

```java
BSLLexer lexer = new BSLLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
BSLParser parser = new BSLParser(tokens);
this.tree  = parser.program();
```
**Antlr2CAstTranslator.java** - Uncomment the following line to print the parse tree:
```java
System.out.println(tree.toStringTree(parser));

```

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
return binaryNode;

```

## Module 4

#### Module 4 Solution:

**RacketPrimitiveTypes.java** - Primitive types in Racket:

```java
primNameMap.put("b", new RacketPrimitiveTypeMap("bool", TypeReference.Boolean.getName().toString()));
primNameMap.put("c", new RacketPrimitiveTypeMap("char", TypeReference.Char.getName().toString()));
```

## Module 5

#### Module 5 Solution:

**GenerateIR.java** - Code to generate IR:

```java
for(IClass c: cha) {
                for (IMethod m : c.getDeclaredMethods()) {
                    if (!m.isSynthetic()) {
                        IRFactory<IMethod> f = AstIRFactory.makeDefaultFactory();
                        IR ir = f.makeIR(m, Everywhere.EVERYWHERE, SSAOptions.defaultOptions());
                        TypeInference t1 = TypeInference.make(ir, true);
                        System.out.println(ir);
                    }
                }
            }
```

## Module 6

#### Module 6 Solution:

**Antlr2CAstTranslator.java** - Code for creating CAstNode for conditional statements:

```java
CAstNode if_stmt = visit(n.expr(0),context); 
            CAstNode then_stmt = makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visit(n.expr(1),context));
            CAstNode else_stmt = makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visit(n.expr(2),context));
            return makeNode(context, fFactory, n, CAstNode.IF_STMT, if_stmt, then_stmt, else_stmt);
```


