package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class While extends Statement {
    private Expression expression;
    private Statement statement;

    public While() {
        expression = null;
        statement = null;
    }

    public void init(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
        if (expression.type != Type.BOOL) {
            expression.error("boolean required in while");
        }
    }

    @Override
    public void generate(int begin, int first) {
        after = first;
        expression.jumping(0, first);
        int label = newLabel();
        emitLabel(label);
        statement.generate(label, begin);
        emit("goto L" + begin);
    }
}
