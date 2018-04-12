package com.lixin.interpreter;

/**
 * @author lixin
 */
public class Statement extends Node {
    public Statement() {
    }

    public static Statement Null = new Statement();

    /**
     * @param begin 语句代码的开始处
     * @param first 语句代码之后的第一条指令
     */
    public void generate(int begin, int first) {
    }

    /**
     * 保存语句的下一条指令的标号
     */
    int after = 0;

    /**
     * 用于 break 语句
     */
    public static Statement Enclosing = Statement.Null;
}
