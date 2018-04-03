package com.lixin.lexer;

/**
 * @author lixin
 */
public class Number extends Token {
    public final int value;

    public Number(int value) {
        super(Tag.NUM);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
