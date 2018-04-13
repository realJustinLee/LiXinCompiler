package com.lixin.symbols;

import com.lixin.lexer.Tag;
import com.lixin.lexer.Word;

import java.util.Arrays;

/**
 * @author lixin
 */
public class Type extends Word {
    public int width;

    public Type(String lexeme, int tag, int width) {
        super(lexeme, tag);
        this.width = width;
    }

    public static final Type
            INT = new Type("int", Tag.BASIC, 4),
            FLOAT = new Type("float", Tag.BASIC, 8),
            CHAR = new Type("char", Tag.BASIC, 1),
            BOOL = new Type("bool", Tag.BASIC, 1);

    public static boolean numeric(Type type) {
        return Arrays.asList(new Type[]{FLOAT, INT, FLOAT}).contains(type);
    }

    public static Type max(Type type1, Type type2) {
        /*The explanation of the working codes.

        if (!numeric(type1) || !numeric(type2)) {
            return null;
        } else if (type1 == Type.FLOAT || type2 == FLOAT) {
            return Type.FLOAT;
        } else if (type1 == Type.INT || type2 == Type.INT) {
            return Type.INT;
        } else {
            return Type.CHAR;
        }*/
        return numeric(type1) && numeric(type2) ? type1 == FLOAT || type2 == FLOAT ? FLOAT : type1 == INT || type2 == INT ? INT : CHAR : null;
    }
}
