package com.ibm.wala.cast.racket.translator;


import com.ibm.wala.cast.ir.translator.RewritingTranslatorToCAst;
import com.ibm.wala.cast.ir.translator.TranslatorToCAst;
import com.ibm.wala.cast.tree.CAst;
import com.ibm.wala.cast.tree.impl.CAstImpl;
import com.ibm.wala.classLoader.ModuleEntry;
import com.ibm.wala.classLoader.SourceFileModule;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class CAstRacketTranslator extends RewritingTranslatorToCAst {
    public CAstRacketTranslator(ModuleEntry m) throws IOException {
        super(m, new Antlr2CAstTranslator<>(new CAstImpl(), m, m.getName()));
    }
    public TranslatorToCAst make(CAst ast, ModuleEntry M) throws IOException {
        return new CAstRacketTranslator(M );
    }
}

