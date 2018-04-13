package com.lixin.main;

import com.lixin.lexer.Lexer;
import com.lixin.parser.Parser;

import java.io.IOException;

/**
 * @author lixin
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Lexer lexer = new Lexer();
        Parser parser = new Parser(lexer);
        parser.program();
        System.out.write('\n');
    }
}
