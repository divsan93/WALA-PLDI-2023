package com.ibm.wala.cast.racket.types;

import com.ibm.wala.cast.tree.CAstType;
import com.ibm.wala.types.TypeReference;
import com.ibm.wala.util.collections.HashMapFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class RacketPrimitiveTypes{
    public static final Map<String, RacketPrimitiveTypeMap> primNameMap = HashMapFactory.make();
public static class RacketPrimitiveTypeMap implements CAstType.Primitive {
    public String fLongName;

    public String fShortName;

    private RacketPrimitiveTypeMap(String longName, String shortName) {
        fLongName = longName;
        fShortName = shortName;
    }

    @Override
    public String getName() {
        return fShortName;
    }

    public String getLongName() {
        return fLongName;
    }

    @Override
    public Collection<CAstType> getSupertypes() {
        return Collections.emptyList();
    }
}
    public static String getShortName(String longName) {
        return primNameMap.get(longName).getName();
    }

    public static RacketPrimitiveTypeMap lookupType(String longName) {
        return primNameMap.get(longName);
    }
    static {
        primNameMap.put("b", new RacketPrimitiveTypeMap("bool", TypeReference.Boolean.getName().toString()));
        primNameMap.put("i", new RacketPrimitiveTypeMap("int", TypeReference.Int.getName().toString()));
        primNameMap.put("c", new RacketPrimitiveTypeMap("char", TypeReference.Char.getName().toString()));
    }
}
