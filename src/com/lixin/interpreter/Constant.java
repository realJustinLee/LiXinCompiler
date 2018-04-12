package com.lixin.interpreter;

import com.lixin.lexer.Numeric;
import com.lixin.lexer.Token;
import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Constant extends ExpressionNode {
    public Constant(Token token, Type type) {
        super(token, type);
    }

    public Constant(int input) {
        super(new Numeric(input), Type.Int);
    }

    public static final Constant
            True = new Constant(Word.True, Type.Bool),
            False = new Constant(Word.False, Type.Bool);

    @Override
    public void jumping(int positive, int negative) {
        if (this == True && positive != 0) {
            emit("goto L" + positive);
        } else if (this == False && negative != 0) {
            emit("goto L" + negative);
        }
    }
}
