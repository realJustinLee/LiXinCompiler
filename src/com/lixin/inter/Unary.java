package com.lixin.inter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Unary extends Operator {
    public ExpressionNode node;

    public Unary(Token token, ExpressionNode node) {
        super(token, null);
        this.node = node;
        type = Type.max(Type.Int, node.type);
        if (type == null) {
            error("type error");
        }
    }

    @Override
    public ExpressionNode generate() {
        return new Unary(operator, node.reduce());
    }

    @Override
    public String toString() {
        return operator.toString() + " " + node.toString();
    }
}
