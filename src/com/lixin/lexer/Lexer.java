package com.lixin.lexer;

import com.lixin.symbols.Type;

import java.io.IOException;
import java.util.Hashtable;

/**
 * @author lixin
 */
public class Lexer {
    public static int line = 1;
    private char peek = ' ';
    private Hashtable<String, Word> words = new Hashtable<>();

    private void reserve(Word word) {
        words.put(word.lexeme, word);
    }

    public Lexer() {
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.TRUE);
        reserve(Word.FALSE);
        reserve(Type.INT);
        reserve(Type.CHAR);
        reserve(Type.BOOL);
        reserve(Type.FLOAT);
    }

    private void readChar() throws IOException {
        peek = (char) System.in.read();
    }

    private boolean readChar(char c) throws IOException {
        readChar();
        if (peek != c) {
            return false;
        } else {
            peek = ' ';
            return true;
        }
    }

    public Token scan() throws IOException {
        /*
        for (; ; readChar()) {
            if (peek == ' ' || peek == '\t'|| peek == '\r') {
                continue;
            } else if (peek == '\n') {
                line++;
            } else {
                break;
            }
        }
        */

        outerLoop:
        while (true) {
            switch (peek) {
                case ' ':
                case '\t':
                case '\r':
                    readChar();
                    continue;
                case '\n':
                    line++;
                    break;
                default:
                    break outerLoop;
            }
            readChar();
        }

        switch (peek) {
            case '&':
                return readChar('&') ? Word.AND : new Token('&');
            case '|':
                return readChar('|') ? Word.OR : new Token('|');
            case '=':
                return readChar('=') ? Word.EQUAL : new Token('=');
            case '!':
                return readChar('=') ? Word.NOT_EQUAL : new Token('!');
            case '<':
                return readChar('=') ? Word.LESS_EQUAL : new Token('<');
            case '>':
                return readChar('=') ? Word.GREATER_EQUAL : new Token('>');
            default:
                break;
        }

        if (Character.isDigit(peek)) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(peek, 10);
                readChar();
            } while (Character.isDigit(peek));
            if (peek != '.') {
                return new Numeric(value);
            }


            // float preciseValue = value;
            float preciseValue;
            // Base Multiplier 默认 Decimal
            float baseMultiplier = 10;
            /*
            while (true) {
                readChar();
                if (!Character.isDigit(peek)) {
                    break;
                }
                preciseValue += Character.digit(peek, 10) / baseMultiplier;
                baseMultiplier *= 10;
            }
            */
            // 若删除下句则float会解析错误
            readChar();
            for (preciseValue = value; Character.isDigit(peek); readChar()) {
                preciseValue += Character.digit(peek, 10) / baseMultiplier;
                baseMultiplier *= 10;
            }
            return new Real(preciseValue);
        }
        if (Character.isLetter(peek)) {
            StringBuilder builder = new StringBuilder();
            do {
                builder.append(peek);
                readChar();
            } while (Character.isLetterOrDigit(peek));
            String string = builder.toString();
            Word word = words.get(string);
            if (word != null) {
                return word;
            }
            word = new Word(string, Tag.IDENTIFIER);
            words.put(string, word);
            return word;
        }
        Token token = new Token(peek);
        peek = ' ';
        return token;
    }
}
