package com.ibm.wala.cast.racket.types;

import com.ibm.wala.cast.ir.translator.AstTranslator;
import com.ibm.wala.cast.types.AstTypeReference;
import com.ibm.wala.core.util.strings.Atom;
import com.ibm.wala.types.ClassLoaderReference;
import com.ibm.wala.types.TypeReference;

public class RacketTypes extends AstTypeReference {

    public static final String str = "Racket";

    public static final String loaderName = "RacketLoader";

    public static final Atom rName = Atom.findOrCreateUnicodeAtom(str);

    public static final Atom rLoaderName = Atom.findOrCreateUnicodeAtom(loaderName);

    public static final ClassLoaderReference racketLoader =
            new ClassLoaderReference(rLoaderName, rName, null);


    public static final TypeReference Root = TypeReference.findOrCreate(racketLoader, rootTypeName);
    public static final TypeReference Function = TypeReference.findOrCreate(racketLoader, "LFunction");

    public static final TypeReference Script = TypeReference.findOrCreate(racketLoader, "LScript");
    public static final TypeReference BOOLEAN = TypeReference.findOrCreate(racketLoader, "LBoolean");
    public static final TypeReference Null = TypeReference.findOrCreate(racketLoader, "LNull");
}

