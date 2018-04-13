package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Unary extends Operator {
    public Expression expression;

    public Unary(Token token, Expression expression) {
        super(token, null);
        this.expression = expression;
        type = Type.max(Type.INT, expression.type);
        if (type == null) {
            error("type error");
        }
    }

    @Override
    public Expression generate() {
        return new Unary(operator, expression.reduce());
    }

    @Override
    public String toString() {
        return operator.toString() + " " + expression.toString();
    }
}
