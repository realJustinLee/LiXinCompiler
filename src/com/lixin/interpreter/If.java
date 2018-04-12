package com.lixin.interpreter;

import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class If extends Statement {
    Expression node;
    Statement statement;

    public If(Expression node, Statement statement) {
        this.node = node;
        this.statement = statement;
        if (node.type != Type.Bool) {
            node.error("boolean required in if");
        }
    }

    @Override
    public void generate(int begin, int first) {
        // statement 的代码标号
        int label = newLabel();
        node.jumping(0, first);
        emitLabel(label);
        statement.generate(label, first);
    }
}
