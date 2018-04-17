package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Do extends Statement {
    private Expression node;
    private Statement statement;

    public Do() {
        node = null;
        statement = null;
    }

    //  public void init(Expression expression1, Statement statement)

    public void init(Statement statement, Expression node) {
        this.statement = statement;
        this.node = node;
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
