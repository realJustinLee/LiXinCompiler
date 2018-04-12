package com.lixin.parser;

import com.lixin.lexer.Lexer;
import com.lixin.lexer.Token;
import com.lixin.symbols.Environment;

import java.io.IOException;

/**
 * @author lixin
 */
public class Parser {
    private Lexer lexer;
    private Token look;
    Environment top = null;
    int used = 0;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        move();
    }

    void move() throws IOException {
        look = lexer.scan();
    }

    void error(String message) {
        throw new Error("near line " + Lexer.line + ": " + message);
    }

    void match(int tag) throws IOException {
        if (look.tag == tag) {
            move();
        } else error("syntax error");
    }

    public void program() {

    }

}
