package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Set extends Statement {
    public Identifier identifier;
    public Expression node;

    public Set(Identifier identifier, Expression node) {
        this.identifier = identifier;
        this.node = node;
        if (check(identifier.type, node.type) == null) {

        }
    }

    public Type check(Type type1, Type type2) {
        /*if (Type.numeric(type1) && Type.numeric(type2)) {
            return type2;
        } else if (type1 == Type.Bool && type2 == Type.Bool) {
            return type2;
        } else {
            return null;
        }*/
        return Type.numeric(type1) && Type.numeric(type2) || type1 == Type.Bool && type2 == Type.Bool ? type2 : null;
    }

    @Override
    public void generate(int begin, int first) {
        emit(identifier.toString() + " = " + node.generate().toString());
    }
}
