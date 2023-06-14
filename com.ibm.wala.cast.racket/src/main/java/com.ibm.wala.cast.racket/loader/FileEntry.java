package com.ibm.wala.cast.racket.loader;

import com.ibm.wala.classLoader.Module;
import com.ibm.wala.classLoader.ModuleEntry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileEntry implements ModuleEntry {
    String filename;

    public FileEntry(String filename){this.filename=filename;}
    @Override
    public String getName() {
        return filename;
    }

    @Override
    public boolean isClassFile() {
        return false;
    }

    @Override
    public boolean isSourceFile() {
        return true;
    }

    @Override
    public InputStream getInputStream() {
        try {
            return new FileInputStream(new File(filename));
        } catch (IOException e) {
            assert false : e;
            return null;
        }
    }

    @Override
    public boolean isModuleFile() {
        return false;
    }

    @Override
    public Module asModule() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public Module getContainer() {
        return null;
    }
}
