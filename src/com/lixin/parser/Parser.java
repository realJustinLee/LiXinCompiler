package com.lixin.parser;

import com.lixin.interpreter.*;
import com.lixin.lexer.*;
import com.lixin.symbols.Array;
import com.lixin.symbols.Environment;
import com.lixin.symbols.Type;

import java.io.IOException;

/**
 * @author lixin
 */
public class Parser {
    private Lexer lexer;
    private Token look;
    Environment top = null;
    int used = 0;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        move();
    }

    void move() throws IOException {
        look = lexer.scan();
    }

    void error(String message) {
        throw new Error("near line " + Lexer.line + ": " + message);
    }

    void match(int tag) throws IOException {
        if (look.tag == tag) {
            move();
        } else {
            error("syntax error");
        }
    }

    public void program() throws IOException {
        Statement statement = block();
        int begin = statement.newLabel();
        int after = statement.newLabel();
        statement.emitLabel(begin);
        statement.generate(begin, after);
        statement.emitLabel(after);
    }

    Statement block() throws IOException {
        match('{');
        Environment savedEnvironment = top;
        top = new Environment(top);
        deClause();
        Statement statement = statements();
        match('}');
        top = savedEnvironment;
        return statement;
    }

    void deClause() throws IOException {
        while (look.tag == Tag.BASIC) {
            Type type = type();
        }
    }

    Type type() throws IOException {
        Type type = (Type) look;
        match(Tag.BASIC);
        if (look.tag != '[') {
            return type;
        } else {
            return dims(type);
        }
    }

    /**
     * TODO: 重命名
     */
    Type dims(Type type) throws IOException {
        match('[');
        Token token = look;
        match(Tag.NUM);
        match(']');
        if (look.tag == '[') {
            type = dims(type);
        }
        return new Array(((Numeric) token).value, type);
    }

    Statement statements() throws IOException {
        if (look.tag == '}') {
            return Statement.Null;
        } else {
            return new Sequence(statement(), statements());
        }
    }

    Statement statement() throws IOException {
        Expression node;
        Statement statement, statement1, statement2;
        Statement savedStatement;
        switch (look.tag) {
            case ';':
                move();
                return Statement.Null;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                node = bool();
                match(')');
                statement1 = statement();
                if (look.tag != Tag.FALSE) {
                    return new If(node, statement1);
                }
                match(Tag.FALSE);
                statement2 = statement();
                return new Else(node, statement1, statement2);
            case Tag.WHILE:
                While whileNode = new While();
                savedStatement = Statement.Enclosing;
                Statement.Enclosing = whileNode;
                match(Tag.WHILE);
                match('(');
                node = bool();
                match(')');
                statement1 = statement();
                whileNode.init(node, statement1);
                Statement.Enclosing = savedStatement;
                return whileNode;
            case Tag.DO:
                Do doNode = new Do();
                savedStatement = Statement.Enclosing;
                Statement.Enclosing = doNode;
                match(Tag.DO);
                statement1 = statement();
                match(Tag.WHILE);
                match('(');
                node = bool();
                match(')');
                match(';');
                doNode.init(statement1, node);
                Statement.Enclosing = savedStatement;
                return doNode;
            case Tag.BREAK:
                match(Tag.BREAK);
                match(';');
                return new Break();
            case '{':
                return block();
            default:
                return assign();
        }
    }

    Statement assign() throws IOException {
        Statement statement;
        Token token = look;
        match(Tag.ID);
        Id id = top.get(token);
        if (id == null) {
            error(token.toString() + " undeclared");
        }
        if (look.tag == '=') {
            move();
            statement = new Set(id, bool());
        } else {
            Access access = offset(id);
            match('=');
            statement = new SetElem(access, bool());
        }
        match(';');
        return statement;
    }

    Expression bool() throws IOException {
        Expression node = join();
        while (look.tag == Tag.OR) {
            Token token = look;
            move();
            node = new Or(token, node, join());
        }
        return node;
    }

    Expression join() throws IOException {
        Expression node = equality();
        while (look.tag == Tag.AND) {
            Token token = look;
            move();
            node = new And(token, node, equality());
        }
        return node;
    }

    Expression equality() throws IOException {
        Expression node = relation();
        while (look.tag == Tag.EQ || look.tag == Tag.NE) {
            Token token = look;
            move();
            return new Relation(token, node, relation());
        }
        return node;
    }

    Expression relation() throws IOException {
        Expression node = expression();
        switch (look.tag) {
            case '<':
            case Tag.LE:
            case Tag.GE:
            case '>':
                Token token = look;
                move();
                return new Relation(token, node, expression());
            default:
                return node;
        }
    }

    Expression expression() throws IOException {
        Expression node = term();
        while (look.tag == '+' || look.tag == '-') {
            Token token = look;
            move();
            return new Arithmetic(token, node, term());
        }
        return node;
    }

    Expression term() throws IOException {
        Expression node = unary();
        while (look.tag == '*' || look.tag == '/') {
            Token token = look;
            move();
            return new Arithmetic(token, node, unary());
        }
        return node;
    }

    Expression unary() throws IOException {
        if (look.tag == '-') {
            move();
            return new Unary(Word.minus, unary());
        } else if (look.tag == '!') {
            Token token = look;
            move();
            return new Not(token, unary());
        } else {
            return factor();
        }
    }

    Expression factor() throws IOException {
        Expression node = null;
        switch (look.tag) {
            case '(':
                move();
                node = bool();
                match(')');
                return node;
            case Tag.NUM:
                node = new Constant(look, Type.Int);
                move();
                return node;
            case Tag.REAL:
                node = new Constant(look, Type.Float);
                move();
                return node;
            case Tag.TRUE:
                node = Constant.True;
                move();
                return node;
            case Tag.FALSE:
                node = Constant.False;
                move();
                return node;
            case Tag.ID:
                String stringLook = look.toString();
                Id id = top.get(look);
                if (id == null) {
                    error(look.toString() + " undeclared");
                }
                move();
                if (look.tag != '[') {
                    return id;
                } else {
                    return offset(id);
                }
            default:
                error("syntax error");
                return node;
        }
    }

    Access offset(Id array) throws IOException {
        Expression index;
        Expression width;
        Expression token1;
        Expression token2;
        Expression location;
        Type type = array.type;
        match('[');
        index = bool();
        match(']');
        type = ((Array) type).of;
        width = new Constant(type.width);
        token1 = new Arithmetic(new Token('*'), index, width);
        location = token1;
        while (look.tag == '[') {
            match('[');
            index = bool();
            match(']');
            type = ((Array) type).of;
            width = new Constant(type.width);
            token1 = new Arithmetic(new Token('*'), index, width);
            token2 = new Arithmetic(new Token('+'), location, token1);
            location = token2;
        }
        return new Access(array, location, type);
    }
}
