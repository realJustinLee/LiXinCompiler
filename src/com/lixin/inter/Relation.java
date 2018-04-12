package com.lixin.inter;

import com.lixin.lexer.Token;
import com.lixin.symbols.Array;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Relation extends Logical {
    public Relation(Token token, ExpressionNode node1, ExpressionNode node2) {
        super(token, node1, node2);
    }

    @Override
    public Type check(Type type1, Type type2) {
        //  if (type1 instanceof Array || type2 instanceof Array) {
        //      return null;
        //  } else if (type1 == type2) {
        //      return Type.Bool;
        //  } else {
        //      return null;
        //  }
        return type1 instanceof Array || type2 instanceof Array ? null : type1 == type2 ? Type.Bool : null;
    }

    @Override
    public void jumping(int positive, int negative) {
        ExpressionNode nodeA = node1.reduce();
        ExpressionNode nodeB = node2.reduce();
        String test = nodeA.toString() + " " + operator.toString() + " " + nodeB.toString();
        emitJumps(test, positive, negative);
    }
}
