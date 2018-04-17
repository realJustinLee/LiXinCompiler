package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Expression extends Node {
    Token operator;
    public Type type;

    Expression(Token operator, Type type) {
        this.operator = operator;
        this.type = type;
    }

    public Expression generate() {
        return this;
    }

    public Expression reduce() {
        return this;
    }

    public void jumping(int positive, int negative) {
        emitJumps(toString(), positive, negative);
    }

    void emitJumps(String condition, int positive, int negative) {
        if (positive != 0 && negative != 0) {
            emit("if " + condition + " goto L" + positive);
            emit("goto L" + negative);
        } else if (positive != 0) {
            emit("if " + condition + " goto L" + positive);
        } else if (negative != 0) {
            emit("if not " + condition + " goto L" + negative);
        }
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
