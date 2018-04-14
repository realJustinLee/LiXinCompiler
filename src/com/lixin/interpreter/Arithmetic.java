package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Arithmetic extends Operator {
    private Expression expression1, expression2;

    public Arithmetic(Token token, Expression expression1, Expression expression2) {
        super(token, null);
        this.expression1 = expression1;
        this.expression2 = expression2;
        type = Type.max(expression1.type, expression2.type);
        if (type == null) {
            error("type error");
        }
    }

    @Override
    public Expression generate() {
        return new Arithmetic(operator, expression1.reduce(), expression2.reduce());
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operator.toString() + " " + expression2.toString();
    }
}
