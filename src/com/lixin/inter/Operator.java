package com.lixin.inter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Type;

/**
 * @author justinadam
 */
public class Operator extends ExpressionNode {
    public Operator(Token token, Type type) {
        super(token, type);
    }

    @Override
    public ExpressionNode reduce() {
        ExpressionNode expressionNode = generate();
        Temp temp = new Temp(type);
        emit(temp.toString() + "=" + expressionNode.toString());
        return temp;
    }
}
