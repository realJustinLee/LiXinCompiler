package com.lixin.interpreter;

/**
 * @author lixin
 */
public class Break extends Statement {
    Statement statement;

    public Break() {
        if (Statement.Enclosing == Statement.Null) {
            error("unenclosed break");
        }
        statement = Statement.Enclosing;
    }

    @Override
    public void generate(int begin, int first) {
        emit("goto L" + statement.after);
    }
}
