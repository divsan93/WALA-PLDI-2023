# WALA_Racket

## Module 1

#### Links:

Download IntelliJ IDEA - https://www.jetbrains.com/idea/download/#section=mac

Versions - https://www.jetbrains.com/idea/download/other.html

Racket Grammar - https://github.com/antlr/grammars-v4/tree/master/racket-bsl

Racket Docs - https://docs.racket-lang.org/index.html

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
