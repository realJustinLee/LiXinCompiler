package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Set extends Statement {
    public Id id;
    public Expression node;

    public Set(Id id, Expression node) {
        this.id = id;
        this.node = node;
        if (check(id.type, node.type) == null) {

        }
    }

    public Type check(Type type1, Type type2) {
        //  if (Type.numeric(type1) && Type.numeric(type2)) {
        //      return type2;
        //  } else if (type1 == Type.Bool && type2 == Type.Bool) {
        //      return type2;
        //  } else {
        //      return null;
        //  }
        return Type.numeric(type1) && Type.numeric(type2) || type1 == Type.Bool && type2 == Type.Bool ? type2 : null;
    }

    @Override
    public void generate(int begin, int first) {
        emit(id.toString() + " = " + node.generate().toString());
    }
}
