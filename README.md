# WALA Racket

## Module 4

#### Module 4 Solution:

Primitive types in Racket:

```java
primNameMap.put("b", new RacketPrimitiveTypeMap("bool", TypeReference.Boolean.getName().toString()));
primNameMap.put("c", new RacketPrimitiveTypeMap("char", TypeReference.Char.getName().toString()));
```

## Module 5

#### Module 5 Solution:

Code to generate IR:

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
