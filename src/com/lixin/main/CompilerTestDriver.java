package com.lixin.main;

import java.io.File;

/**
 * @author lixin
 */
public class CompilerTestDriver {
    public static void main(String[] args) {
        File fileIn = new File("/Users/lixin/Downloads/in.txt");
        File fileOut = new File("/Users/lixin/Downloads/out.txt");
        Compiler compiler = new Compiler();
        compiler.compile(fileIn, fileOut);
    }
}
