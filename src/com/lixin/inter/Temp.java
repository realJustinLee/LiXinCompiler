package com.lixin.inter;

import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Temp extends ExpressionNode {
    static int count = 0;
    int number;

    public Temp(Type type) {
        super(Word.temp, type);
        number = ++count;
    }

    @Override
    public String toString() {
        return "t" + number;
    }
}
