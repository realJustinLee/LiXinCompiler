package com.lixin.interpreter;

import com.lixin.lexer.Numeric;
import com.lixin.lexer.Token;
import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Constant extends Expression {
    public Constant(Token token, Type type) {
        super(token, type);
    }

    public Constant(int input) {
        super(new Numeric(input), Type.INT);
    }

    public static final Constant
            TRUE = new Constant(Word.TRUE, Type.BOOL),
            FALSE = new Constant(Word.FALSE, Type.BOOL);

    @Override
    public void jumping(int positive, int negative) {
        if (this == TRUE && positive != 0) {
            emit("goto L" + positive);
        } else if (this == FALSE && negative != 0) {
            emit("goto L" + negative);
        }
    }
}
