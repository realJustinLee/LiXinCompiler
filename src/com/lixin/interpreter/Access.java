package com.lixin.interpreter;

import com.lixin.lexer.Tag;
import com.lixin.lexer.Word;
import com.lixin.symbols.Type;

/**
 * @author lixin
 */
public class Access extends Operator {
    public Id array;
    public Expression index;

    /**
     * @param array 数组
     * @param index 下标
     * @param type  数组元素类型
     */
    public Access(Id array, Expression index, Type type) {
        super(new Word("[]", Tag.INDEX), type);
        this.array = array;
        this.index = index;
    }

    @Override
    public Expression generate() {
        return new Access(array, index.reduce(), type);
    }

    @Override
    public void jumping(int positive, int negative) {
        emitJumps(reduce().toString(), positive, negative);
    }

    @Override
    public String toString() {
        return array.toString() + " [ " + index.toString() + " ]";
    }
}
