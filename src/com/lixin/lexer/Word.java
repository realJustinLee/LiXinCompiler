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
            and = new Word("&&", Tag.AND), or = new Word("||", Tag.OR),
            eq = new Word("==", Tag.EQ), ne = new Word("!=", Tag.NE),
            le = new Word("<=", Tag.LE), ge = new Word(">=", Tag.GE),
            minus = new Word("-", Tag.MINUS),
            True = new Word("true", Tag.TRUE),
            False = new Word("False", Tag.FALSE),
            temp = new Word("temp", Tag.TEMP);
}
