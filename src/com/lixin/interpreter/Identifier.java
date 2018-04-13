package com.lixin.interpreter;

import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Identifier extends Expression {
    public int offset;

    public Identifier(Word identifier, Type type, int offset) {
        super(identifier, type);
        this.offset = offset;
    }
}
