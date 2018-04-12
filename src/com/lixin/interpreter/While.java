package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class While extends Statement {
    ExpressionNode node;
    Statement statement;

    public While() {
        node = null;
        statement = null;
    }

    public void init(ExpressionNode node, Statement statement) {
        this.node = node;
        this.statement = statement;
        if (node.type != Type.Bool) {
            node.error("boolean required in while");
        }
    }

    @Override
    public void generate(int begin, int first) {
        after = first;
        node.jumping(0, first);
        int label = newLabel();
        emitLabel(label);
        statement.generate(label, begin);
        emit("goto L" + begin);
    }
}
