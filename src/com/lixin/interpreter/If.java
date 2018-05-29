package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class If extends Statement {
    private Expression expression;
    private Statement statement;

    public If(Expression expression, Statement statement) {
        this.expression = expression;
        this.statement = statement;
        if (expression.type != Type.BOOL) {
            expression.error("boolean required in if");
        }
    }

    @Override
    public void generate(int begin, int first) {
        // statement 的代码标号
        int label = newLabel();
        expression.jumping(0, first);
        emitLabel(label);
        statement.generate(label, first);
    }
}
