package com.ibm.wala.cast.racket.IRGen;
import com.ibm.wala.analysis.typeInference.TypeInference;
import com.ibm.wala.cast.ir.ssa.AstIRFactory;
import com.ibm.wala.cast.racket.ipa.callgraph.RacketCallGraphUtil;
import com.ibm.wala.cast.racket.loader.FileEntry;
import com.ibm.wala.cast.racket.translator.CAstRacketTranslator;
import com.ibm.wala.classLoader.IClass;
import com.ibm.wala.classLoader.IMethod;
import com.ibm.wala.ipa.callgraph.impl.Everywhere;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.ssa.IR;
import com.ibm.wala.ssa.IRFactory;
import com.ibm.wala.ssa.SSAOptions;
import com.ibm.wala.util.WalaException;


import java.io.IOException;


public class GenerateIR {
    public static void main(String[] args) throws IOException, WalaException {
        String filename = System.getProperty("user.dir")+"/com.ibm.wala.cast.racket/src/test/resources/DemoPrograms/add.rkt";
        try {
            FileEntry modules = new FileEntry(filename);
            RacketCallGraphUtil.setTranslatorFactory(new CAstRacketTranslator(modules));
            IClassHierarchy cha = RacketCallGraphUtil.makeHierarchyForScripts(filename);
        }
        catch (Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace(System.err);
        }

    }
}
