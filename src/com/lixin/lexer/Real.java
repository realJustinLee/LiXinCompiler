package com.lixin.lexer;

/**
 * @author lixin
 */
public class Real extends Token {
    public final float value;

    public Real(float value) {
        super(Tag.REAL);
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
