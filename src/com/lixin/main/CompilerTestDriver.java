package com.lixin.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author lixin
 */
public class CompilerTestDriver {
    public static void main(String[] args) {
        Compiler compiler = new Compiler();
        // 文件方法
        File fileIn = new File("/Users/lixin/Downloads/in.txt");
        File fileOut = new File("/Users/lixin/Downloads/out.txt");
        compiler.compile(fileIn, fileOut);

        // String 参数方法
        String in = "{\n" +
                "\tint i; int j; float v; float x; float[100] a;\n" +
                "\twhile( true ) {\n" +
                "\t\tdo i = i+1; while( a[i] < v);\n" +
                "\t\tdo j = j-1; while( a[j] > v);\n" +
                "\t\tif( i >= j ) break;\n" +
                "\t\tx = a[i]; a[i] = a[j]; a[j] = x;\n" +
                "\t}\n" +
                "}\n" +
                "{}";
        String out = compiler.compile(in);
        System.out.print(out);
    }
}
