package com.lixin.interpreter;

import com.lixin.lexer.Token;

/**
 * @author lixin
 */
public class Not extends Logical {
    public Not(Token token, Expression expression2) {
        super(token, expression2, expression2);
    }

    @Override
    public void jumping(int positive, int negative) {
        expression2.jumping(negative, positive);
    }

    @Override
    public String toString() {
        return operator.toString() + " " + expression2.toString();
    }
}
