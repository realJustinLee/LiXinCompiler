package com.lixin.interpreter;

/**
 * @author lixin
 */
public class Sequence extends Statement {
    Statement statement1;
    Statement statement2;

    public Sequence(Statement statement1, Statement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public void generate(int begin, int first) {
        if (statement1 == Statement.Null) {
            statement2.generate(begin, first);
        } else if (statement2 == Statement.Null) {
            statement1.generate(begin, first);
        } else {
            int label = newLabel();
            statement1.generate(begin, label);
            emitLabel(label);
            statement2.generate(label, first);
        }
    }
}
