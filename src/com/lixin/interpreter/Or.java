package com.lixin.interpreter;

import com.lixin.lexer.Token;

/**
 * @author lixin
 */
public class Or extends Logical {
    public Or(Token token, Expression expression1, Expression expression2) {
        super(token, expression1, expression2);
    }

    @Override
    public void jumping(int positive, int negative) {
        int label = positive != 0 ? positive : newLabel();
        expression1.jumping(label, 0);
        expression2.jumping(positive, negative);
        if (positive == 0) {
            emitLabel(label);
        }
    }
}
