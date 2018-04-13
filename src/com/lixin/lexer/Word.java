package com.lixin.lexer;

/**
 * @author lixin
 */
public class Word extends Token {
    String lexeme;

    public Word(String lexeme, int tag) {
        super(tag);
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return lexeme;
    }

    public static final Word
            AND = new Word("&&", Tag.AND),
            OR = new Word("||", Tag.OR),
            EQUAL = new Word("==", Tag.EQUAL),
            NOT_EQUAL = new Word("!=", Tag.NOT_EQUAL),
            LESS_EQUAL = new Word("<=", Tag.LESS_EQUAL),
            GREATER_EQUAL = new Word(">=", Tag.GREATER_EQUAL),
            MINUS = new Word("-", Tag.MINUS),
            TRUE = new Word("true", Tag.TRUE),
            FALSE = new Word("false", Tag.FALSE),
            TEMP = new Word("t", Tag.TEMP);
}
