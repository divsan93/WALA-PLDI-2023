# WALA_Racket

## Module 1
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
