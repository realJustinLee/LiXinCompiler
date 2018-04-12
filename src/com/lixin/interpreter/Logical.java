package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Logical extends ExpressionNode {
    public ExpressionNode node1, node2;

    Logical(Token token, ExpressionNode node1, ExpressionNode node2) {
        super(token, null);
        this.node1 = node1;
        this.node2 = node2;
        if (type == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        return (type1 == Type.Bool && type2 == Type.Bool) ? Type.Bool : null;
    }

    @Override
    public ExpressionNode generate() {
        int positive = newLabel();
        int negative = newLabel();
        Temp temp = new Temp(type);
        this.jumping(0, negative);
        emit(temp.toString() + " = true");
        emit("goto L" + positive);
        emitLabel(negative);
        emit(temp.toString() + " = false");
        emitLabel(positive);
        return temp;
    }

    @Override
    public String toString() {
        return node1.toString() + " " + operator.toString() + " " + node2.toString();
    }
}
