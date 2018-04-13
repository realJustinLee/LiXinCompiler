package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Logical extends Expression {
    public Expression expression1, expression2;

    Logical(Token token, Expression expression1, Expression expression2) {
        super(token, null);
        this.expression1 = expression1;
        this.expression2 = expression2;
        if (type == null) {
            error("type error");
        }
    }

    public Type check(Type type1, Type type2) {
        return type1 == Type.BOOL && type2 == Type.BOOL ? Type.BOOL : null;
    }

    @Override
    public Expression generate() {
        int negative = newLabel();
        int after = newLabel();
        Temp temp = new Temp(type);
        this.jumping(0, negative);
        emit(temp.toString() + " = true");
        emit("goto L" + after);
        emitLabel(negative);
        emit(temp.toString() + " = false");
        emitLabel(after);
        return temp;
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operator.toString() + " " + expression2.toString();
    }
}
