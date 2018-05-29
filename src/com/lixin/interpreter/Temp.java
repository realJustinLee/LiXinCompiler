package com.lixin.interpreter;

import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Temp extends Expression {
    private static int count = 0;
    private int number;

    Temp(Type type) {
        super(Word.TEMP, type);
        number = ++count;
    }

    @Override
    public String toString() {
        return "t" + number;
    }
}
