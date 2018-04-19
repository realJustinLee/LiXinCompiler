package com.lixin.interpreter;

import com.lixin.symbols.Array;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class SetElem extends Statement {
    private Identifier array;
    private Expression index;
    private Expression expression;

    public SetElem(Access access, Expression expression) {
        array = access.array;
        index = access.index;
        this.expression = expression;
        if (check(access.type, expression.type) == null) {
            error("type error");
        }
    }

    private Type check(Type type1, Type type2) {
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
        String stringExpression = expression.reduce().toString();
        emit(array.toString() + " [ " + stringIndex + " ] = " + stringExpression);
    }
}
