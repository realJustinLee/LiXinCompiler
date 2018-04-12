package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Arithmetic extends Operator {
    public Expression node1, node2;

    public Arithmetic(Token token, Expression node1, Expression node2) {
        super(token, null);
        this.node1 = node1;
        this.node2 = node2;
        type = Type.max(node1.type, node2.type);
        if (type == null) {
            error("type error");
        }
    }

    @Override
    public Expression generate() {
        return new Arithmetic(operator, node1.reduce(), node2.reduce());
    }

    @Override
    public String toString() {
        return node1.toString() + " " + operator.toString() + " " + node2.toString();
    }
}
