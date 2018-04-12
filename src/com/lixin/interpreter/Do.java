package com.lixin.interpreter;

import com.lixin.symbols.Type;

public class Do extends Statement {
    ExpressionNode node;
    Statement statement;

    public Do() {
        node = null;
        statement = null;
    }

    //public void init(ExpressionNode node, Statement statement)

    public void init(Statement statement, ExpressionNode node) {
        this.node = node;
        this.statement = statement;
        if (node.type != Type.Bool) {
            node.error("boolean required in do");
        }
    }

    @Override
    public void generate(int begin, int first) {
        after = first;
        int label = newLabel();
        statement.generate(begin, label);
        emitLabel(label);
        node.jumping(begin, 0);
    }
}
