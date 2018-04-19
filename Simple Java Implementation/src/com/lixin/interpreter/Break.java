package com.lixin.interpreter;

/**
 * @author lixin
 */
public class Break extends Statement {
    private Statement statement;

    public Break() {
        if (Statement.ENCLOSING == Statement.NULL) {
            error("unenclosed break");
        }
        statement = Statement.ENCLOSING;
    }

    @Override
    public void generate(int begin, int first) {
        emit("goto L" + statement.after);
    }
}
