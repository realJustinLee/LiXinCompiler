package com.lixin.main;

import com.lixin.lexer.Lexer;
import com.lixin.parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * @author lixin
 */
public class Compiler {
    public void compile(File in, File out) {
        try {
            FileInputStream fileInputStream = new FileInputStream(in);
            System.setIn(fileInputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(out);
            PrintStream printStream = new PrintStream(fileOutputStream);
            System.setOut(printStream);
            System.out.println("lxc>");
            Lexer lexer = new Lexer();
            Parser parser = new Parser(lexer);
            parser.program();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
