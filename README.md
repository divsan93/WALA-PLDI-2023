# WALA Racket

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
CAstNode if_stmt = visit(n.expr(0),context); // makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visit(n.expr(0),context));
            CAstNode then_stmt = makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visit(n.expr(1),context));
            CAstNode else_stmt = makeNode(context, fFactory, n, CAstNode.BLOCK_STMT, visit(n.expr(2),context));
            return makeNode(context, fFactory, n, CAstNode.IF_STMT, if_stmt, then_stmt, else_stmt);
```
