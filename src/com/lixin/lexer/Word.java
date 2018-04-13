package com.lixin.lexer;

/**
 * @author lixin
 */
public class Word extends Token {
    public String lexeme;

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
            minus = new Word("-", Tag.MINUS),
            True = new Word("true", Tag.TRUE),
            False = new Word("false", Tag.FALSE),
            temp = new Word("t", Tag.TEMP);
}
