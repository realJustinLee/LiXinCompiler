package com.lixin.interpreter;

import com.lixin.lexer.Token;

/**
 * @author lixin
 */
public class Not extends Logical {
    public Not(Token token, ExpressionNode node2) {
        super(token, node2, node2);
    }

    @Override
    public void jumping(int positive, int negative) {
        node2.jumping(negative, positive);
    }

    @Override
    public String toString() {
        return operator.toString() + " " + node2.toString();
    }
}
