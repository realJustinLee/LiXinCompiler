package com.lixin.inter;

import com.lixin.lexer.Token;

/**
 * @author lixin
 */
public class And extends Logical {
    public And(Token token, ExpressionNode node1, ExpressionNode node2) {
        super(token, node1, node2);
    }

    @Override
    public void jumping(int positive, int negative) {
        int label = negative != 0 ? negative : newLabel();
        node1.jumping(0, negative);
        node2.jumping(positive, negative);
        if (negative == 0) {
            emitLabel(label);
        }
    }
}
