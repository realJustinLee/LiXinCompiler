package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Do extends Statement {
    private Expression expression;
    private Statement statement;

    public Do() {
        expression = null;
        statement = null;
    }

    //  public void init(Expression expression1, Statement statement)

    public void init(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
        if (expression.type != Type.BOOL) {
            expression.error("boolean required in do");
        }
    }

    @Override
    public void generate(int begin, int first) {
        after = first;
        int label = newLabel();
        statement.generate(begin, label);
        emitLabel(label);
        expression.jumping(begin, 0);
    }
}
