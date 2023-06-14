package com.ibm.wala.cast.racket.loader;


import com.ibm.wala.cast.loader.SingleClassLoaderFactory;
import com.ibm.wala.cast.racket.translator.CAstRacketTranslator;
import com.ibm.wala.cast.racket.types.RacketTypes;
import com.ibm.wala.cast.tree.rewrite.CAstRewriterFactory;
import com.ibm.wala.classLoader.IClassLoader;
import com.ibm.wala.ipa.cha.IClassHierarchy;
import com.ibm.wala.types.ClassLoaderReference;

public class RacketLoaderFactory extends SingleClassLoaderFactory {
    protected final CAstRacketTranslator translatorFactory;
    protected final CAstRewriterFactory<?, ?> preprocessor;

    public RacketLoaderFactory(CAstRacketTranslator factory) {
        this(factory, null);
    }

    public RacketLoaderFactory(
            CAstRacketTranslator factory, CAstRewriterFactory<?, ?> preprocessor) {
        this.translatorFactory = factory;
        this.preprocessor = preprocessor;
    }

    @Override
    protected IClassLoader makeTheLoader(IClassHierarchy cha) {
        return new RacketLoader(cha, translatorFactory, preprocessor);
    }

    @Override
    public ClassLoaderReference getTheReference() {
        return RacketTypes.racketLoader;
    }

}
