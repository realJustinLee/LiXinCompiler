package com.lixin.inter;

import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Id extends ExpressionNode {
    public int offset;

    public Id(Word id, Type type, int offset) {
        super(id, type);
        this.offset = offset;
    }
}
