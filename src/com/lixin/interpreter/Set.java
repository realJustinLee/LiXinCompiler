package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Set extends Statement {
    private Identifier identifier;
    private Expression expression;

    public Set(Identifier identifier, Expression expression) {
        this.identifier = identifier;
        this.expression = expression;
        if (check(identifier.type, expression.type) == null) {
            error("type error");
        }
    }

    private Type check(Type type1, Type type2) {
        /*if (Type.numeric(type1) && Type.numeric(type2)) {
            return type2;
        } else if (type1 == Type.BOOL && type2 == Type.BOOL) {
            return type2;
        } else {
            return null;
        }*/
        return Type.numeric(type1) && Type.numeric(type2) || type1 == Type.BOOL && type2 == Type.BOOL ? type2 : null;
    }

    @Override
    public void generate(int begin, int first) {
        emit(identifier.toString() + " = " + expression.generate().toString());
    }
}
