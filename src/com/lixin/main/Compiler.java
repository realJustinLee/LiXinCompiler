package com.lixin.main;

import com.lixin.lexer.Lexer;
import com.lixin.parser.Parser;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

/**
 * @author lixin
 */
public class Compiler {
    public void compile(File in, File out) {
        InputStream sysIn = System.in;
        PrintStream sysOut = System.out;
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
        } finally {
            System.setIn(sysIn);
            System.setOut(sysOut);
        }
    }

    public String compile(String in) {
        InputStream sysIn = System.in;
        PrintStream sysOut = System.out;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(in.getBytes());
            System.setIn(inputStream);
            ByteOutputStream outputStream = new ByteOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);
            System.out.println("lxc>");
            Lexer lexer = new Lexer();
            Parser parser = new Parser(lexer);
            parser.program();
            System.out.println();
            return outputStream.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            System.setIn(sysIn);
            System.setOut(sysOut);
        }
    }
}
