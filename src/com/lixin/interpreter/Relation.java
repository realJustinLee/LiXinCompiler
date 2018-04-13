package com.lixin.interpreter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Array;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Relation extends Logical {
    public Relation(Token token, Expression expression1, Expression expression2) {
        super(token, expression1, expression2);
    }

    @Override
    public Type check(Type type1, Type type2) {
        /*if (type1 instanceof Array || type2 instanceof Array) {
            return null;
        } else if (type1 == type2) {
            return Type.BOOL;
        } else {
            return null;
        }*/
        return type1 instanceof Array || type2 instanceof Array ? null : type1 == type2 ? Type.BOOL : null;
    }

    @Override
    public void jumping(int positive, int negative) {
        Expression expressionA = expression1.reduce();
        Expression expressionB = expression2.reduce();
        String condition = expressionA.toString() + " " + operator.toString() + " " + expressionB.toString();
        emitJumps(condition, positive, negative);
    }
}
