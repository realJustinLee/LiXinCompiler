package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Unary extends Operator {
    public Expression node;

    public Unary(Token token, Expression node) {
        super(token, null);
        this.node = node;
        type = Type.max(Type.INT, node.type);
        if (type == null) {
            error("type error");
        }
    }

    @Override
    public Expression generate() {
        return new Unary(operator, node.reduce());
    }

    @Override
    public String toString() {
        return operator.toString() + " " + node.toString();
    }
}
