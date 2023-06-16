# WALA_Racket

## Module 1

#### Module 1 Solution:

Commands to generate Parser and Lexer
```java
java -jar antlr-4.11.1-complete.jar BSL.g4
javac -cp ./antlr-4.11.1-complete.jar *.java

```

Commands to generate Visitor
```java
java -jar antlr-4.11.1-complete.jar -Dlanguage=Java BSL.g4 -visitor
javac -classpath antlr-4.11.1-complete.jar *.java
```

Adding package name
```java
package com.ibm.wala.cast.racket.Antlr;
```

## Module 2

#### Module 2 Solution:

Code to generate parse tree:

```java
BSLLexer lexer = new BSLLexer(input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
BSLParser parser = new BSLParser(tokens);
this.tree  = parser.program();
```
