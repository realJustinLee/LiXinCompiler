package com.lixin.main;

import java.io.File;

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
        String in = "{" +
                "\tint i; int j; float v; float x; float[100] a;\r\n" +
                "\twhile( true ) {\r\n" +
                "\t\tdo i = i+1; while( a[i] < v);\r\n" +
                "\t\tdo j = j-1; while( a[j] > v);\r\n" +
                "\t\tif( i >= j ) break;\r\n" +
                "\t\tx = a[i]; a[i] = a[j]; a[j] = x;\r\n" +
                "\t}\r\n" +
                "}\r\n" +
                "{}";
        String out = compiler.compile(in);
        System.out.print(out);
    }
}
