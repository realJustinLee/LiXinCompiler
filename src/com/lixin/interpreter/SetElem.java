package com.lixin.interpreter;

import com.lixin.symbols.Array;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class SetElem extends Statement {
    public Identifier array;
    public Expression index;
    public Expression node;

    public SetElem(Access access, Expression node) {
        array = access.array;
        index = access.index;
        this.node = node;
        if (check(access.type, node.type) == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        /*if (type1 instanceof Array || type2 instanceof Array) {
            return null;
        } else if (type1 == type2) {
            return type2;
        } else if (Type.numeric(type1) && Type.numeric(type2)) {
            return type2;
        } else {
            return null;
        }*/
        return type1 instanceof Array || type2 instanceof Array ? null : (type1 == type2) || (Type.numeric(type1) && Type.numeric(type2)) ? type2 : null;
    }

    @Override
    public void generate(int begin, int first) {
        String stringIndex = index.reduce().toString();
        String stringNode = node.reduce().toString();
        emit(array.toString() + " [ " + stringIndex + " ] = " + stringNode);
    }
}
