package com.lixin.symbols;

import com.lixin.lexer.Tag;

/**
 * @author lixin
 */
public class Array extends Type {
    public Type of;
    public int size;

    public Array(int size, Type type) {
        super("[]", Tag.INDEX, size * type.width);
        this.size = size;
        of = type;
    }

    @Override
    public String toString() {
        return "[" + size + "]" + of.toString();
    }
}
