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
    private Environment top = null;
    private int used = 0;

    public Parser(Lexer lexer) throws IOException {
        this.lexer = lexer;
        move();
    }

    private void move() throws IOException {
        look = lexer.scan();
    }

    private void error(String message) {
        throw new Error("near line " + Lexer.line + ": " + message);
    }

    private void match(int tag) throws IOException {
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

    private Statement block() throws IOException {
        match('{');
        Environment savedEnvironment = top;
        top = new Environment(top);
        deClause();
        Statement statement = statements();
        match('}');
        top = savedEnvironment;
        return statement;
    }

    private void deClause() throws IOException {
        while (look.tag == Tag.BASIC) {
            Type type = type();
            Token token = look;
            match(Tag.IDENTIFIER);
            match(';');
            Identifier identifier = new Identifier((Word) token, type, used);
            top.put(token, identifier);
            used += type.width;
        }
    }

    private Type type() throws IOException {
        Type type = (Type) look;
        match(Tag.BASIC);
        if (look.tag != '[') {
            return type;
        } else {
            return deBracket(type);
        }
    }

    /**
     * Original Name: dims
     */
    private Type deBracket(Type type) throws IOException {
        match('[');
        Token token = look;
        match(Tag.NUM);
        match(']');
        if (look.tag == '[') {
            type = deBracket(type);
        }
        return new Array(((Numeric) token).value, type);
    }

    private Statement statements() throws IOException {
        if (look.tag == '}') {
            return Statement.NULL;
        } else {
            return new Sequence(statement(), statements());
        }
    }

    private Statement statement() throws IOException {
        Expression expression;
        // Statement statement;
        Statement statement1, statement2;
        Statement savedStatement;
        switch (look.tag) {
            case ';':
                move();
                return Statement.NULL;
            case Tag.IF:
                match(Tag.IF);
                match('(');
                expression = bool();
                match(')');
                statement1 = statement();
                if (look.tag != Tag.FALSE) {
                    return new If(expression, statement1);
                }
                match(Tag.FALSE);
                statement2 = statement();
                return new Else(expression, statement1, statement2);
            case Tag.WHILE:
                While whileNode = new While();
                savedStatement = Statement.ENCLOSING;
                Statement.ENCLOSING = whileNode;
                match(Tag.WHILE);
                match('(');
                expression = bool();
                match(')');
                statement1 = statement();
                whileNode.init(expression, statement1);
                Statement.ENCLOSING = savedStatement;
                return whileNode;
            case Tag.DO:
                Do doNode = new Do();
                savedStatement = Statement.ENCLOSING;
                Statement.ENCLOSING = doNode;
                match(Tag.DO);
                statement1 = statement();
                match(Tag.WHILE);
                match('(');
                expression = bool();
                match(')');
                match(';');
                doNode.init(statement1, expression);
                Statement.ENCLOSING = savedStatement;
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

    private Statement assign() throws IOException {
        Statement statement;
        Token token = look;
        match(Tag.IDENTIFIER);
        Identifier identifier = top.get(token);
        if (identifier == null) {
            error(token.toString() + " undeclared");
        }
        if (look.tag == '=') {
            move();
            statement = new Set(identifier, bool());
        } else {
            Access access = offset(identifier);
            match('=');
            statement = new SetElem(access, bool());
        }
        match(';');
        return statement;
    }

    private Expression bool() throws IOException {
        Expression expression = join();
        while (look.tag == Tag.OR) {
            Token token = look;
            move();
            expression = new Or(token, expression, join());
        }
        return expression;
    }

    private Expression join() throws IOException {
        Expression expression = equality();
        while (look.tag == Tag.AND) {
            Token token = look;
            move();
            expression = new And(token, expression, equality());
        }
        return expression;
    }

    private Expression equality() throws IOException {
        Expression expression = relation();
        while (look.tag == Tag.EQUAL || look.tag == Tag.NOT_EQUAL) {
            Token token = look;
            move();
            return new Relation(token, expression, relation());
        }
        return expression;
    }

    private Expression relation() throws IOException {
        Expression expression = expression();
        switch (look.tag) {
            case '<':
            case Tag.LESS_EQUAL:
            case Tag.GREATER_EQUAL:
            case '>':
                Token token = look;
                move();
                return new Relation(token, expression, expression());
            default:
                return expression;
        }
    }

    private Expression expression() throws IOException {
        Expression expression = term();
        while (look.tag == '+' || look.tag == '-') {
            Token token = look;
            move();
            return new Arithmetic(token, expression, term());
        }
        return expression;
    }

    private Expression term() throws IOException {
        Expression expression = unary();
        while (look.tag == '*' || look.tag == '/') {
            Token token = look;
            move();
            return new Arithmetic(token, expression, unary());
        }
        return expression;
    }

    private Expression unary() throws IOException {
        if (look.tag == '-') {
            move();
            return new Unary(Word.MINUS, unary());
        } else if (look.tag == '!') {
            Token token = look;
            move();
            return new Not(token, unary());
        } else {
            return factor();
        }
    }

    private Expression factor() throws IOException {
        Expression expression = null;
        switch (look.tag) {
            case '(':
                move();
                expression = bool();
                match(')');
                return expression;
            case Tag.NUM:
                expression = new Constant(look, Type.INT);
                move();
                return expression;
            case Tag.REAL:
                expression = new Constant(look, Type.FLOAT);
                move();
                return expression;
            case Tag.TRUE:
                expression = Constant.TRUE;
                move();
                return expression;
            case Tag.FALSE:
                expression = Constant.FALSE;
                move();
                return expression;
            case Tag.IDENTIFIER:
                // String stringLook = look.toString(); 没用
                Identifier identifier = top.get(look);
                if (identifier == null) {
                    error(look.toString() + " undeclared");
                }
                move();
                if (look.tag != '[') {
                    return identifier;
                } else {
                    return offset(identifier);
                }
            default:
                error("syntax error");
                return expression;
        }
    }

    private Access offset(Identifier array) throws IOException {
        Expression index;
        Expression width;
        Expression token1, token2;
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