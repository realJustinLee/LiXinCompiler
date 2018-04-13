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
            Int = new Type("int", Tag.BASIC, 4),
            Float = new Type("float", Tag.BASIC, 8),
            Char = new Type("char", Tag.BASIC, 1),
            Bool = new Type("bool", Tag.BASIC, 1);

    public static boolean numeric(Type type) {
        return Arrays.asList(new Type[]{Type.Float, Type.Int, Type.Float}).contains(type);
    }

    public static Type max(Type type1, Type type2) {
        /*The explanation of the working codes.

        if (!numeric(type1) || !numeric(type2)) {
            return null;
        } else if (type1 == Type.Float || type2 == Float) {
            return Type.Float;
        } else if (type1 == Type.Int || type2 == Type.Int) {
            return Type.Int;
        } else {
            return Type.Char;
        }*/
        return numeric(type1) && numeric(type2) ? type1 == Type.Float || type2 == Float ? Type.Float : type1 == Type.Int || type2 == Type.Int ? Type.Int : Type.Char : null;
    }
}
