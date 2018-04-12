package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Operator extends Expression {
    public Operator(Token token, Type type) {
        super(token, type);
    }

    @Override
    public Expression reduce() {
        Expression expression = generate();
        Temp temp = new Temp(type);
        emit(temp.toString() + "=" + expression.toString());
        return temp;
    }
}
