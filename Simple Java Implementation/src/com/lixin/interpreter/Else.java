package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Else extends Statement {
    private Expression expression;
    private Statement statement1;
    private Statement statement2;

    public Else(Expression expression, Statement statement1, Statement statement2) {
        this.expression = expression;
        this.statement1 = statement1;
        this.statement2 = statement2;
        if (expression.type != Type.BOOL) {
            expression.error("boolean required in if");
        }
    }

    @Override
    public void generate(int begin, int first) {
        int label1 = newLabel();
        int label2 = newLabel();
        expression.jumping(0, label2);
        emitLabel(label1);
        statement1.generate(label1, first);
        emit("goto L" + first);
        emitLabel(label2);
        statement2.generate(label2, first);
    }
}
