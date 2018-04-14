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

    public void init(Expression node, Statement statement) {
        this.expression = node;
        this.statement = statement;
        if (node.type != Type.BOOL) {
            node.error("boolean required in while");
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
