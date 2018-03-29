package com.lixin.lexer;

/**
 * @author lixin
 */
public class Token {
    public final int tag;

    public Token(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return String.valueOf((char) tag);
    }
}
