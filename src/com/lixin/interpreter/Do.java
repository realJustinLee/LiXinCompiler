package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Do extends Statement {
    Expression node;
    Statement statement;

    public Do() {
        node = null;
        statement = null;
    }

    //  public void init(Expression node, Statement statement)

    public void init(Statement statement, Expression node) {
        this.node = node;
        this.statement = statement;
        if (node.type != Type.BOOL) {
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
