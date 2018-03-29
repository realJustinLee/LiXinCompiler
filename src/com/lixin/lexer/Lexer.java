package com.lixin.lexer;

import com.lixin.symbols.Type;

import java.io.IOException;
import java.util.Hashtable;

/**
 * @author lixin
 */
public class Lexer {
    public static int line = 1;
    char peek = ' ';
    Hashtable words = new Hashtable();

    void reserve(Word word) {
        words.put(word.lexeme, word);
    }

    public Lexer() {
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
    }

    void readChar() throws IOException {
        peek = (char) System.in.read();
    }

    boolean readChar(char c) throws IOException {
        readChar();
        if (peek != c) {
            return false;
        }
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        label:
        while (true) {
            readChar();

            // if (peek == ' ' || peek == '\t') {
            //     // continue;
            // } else if (peek == '\n') {
            //     line++;
            // } else {
            //     break;
            // }

            switch (peek) {
                case ' ':
                case '\t':
                    continue;
                case '\n':
                    line++;
                    break;
                default:
                    break label;
            }
        }
        switch (peek) {
            case '&':
                return readChar('&') ? Word.and : new Token('&');
            case '|':
                return readChar('|') ? Word.and : new Token('|');
            case '=':
                return readChar('=') ? Word.and : new Token('=');
            case '!':
                return readChar('=') ? Word.and : new Token('!');
            case '<':
                return readChar('=') ? Word.and : new Token('<');
            case '>':
                return readChar('=') ? Word.and : new Token('>');
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
                return new Num(value);
            }
            float preciseValue = value;
            float helperDigit = 10;
            while (true) {
                readChar();
                if (!Character.isDigit(peek)) {
                    break;
                }
                preciseValue += Character.digit(peek, 10) / helperDigit;
                helperDigit *= 10;
            }
            return new Real(preciseValue);
        }
        if (Character.isLetter(peek)) {
            StringBuffer buffer = new StringBuffer();
            do {
                buffer.append(peek);
                readChar();
            } while (Character.isLetterOrDigit(peek));
            String string = buffer.toString();
            Word word = (Word) words.get(string);
            if (word != null) {
                return word;
            }
            word = new Word(string, Tag.ID);
            words.put(string, word);
            return word;
        }
        Token token = new Token(peek);
        peek = ' ';
        return token;
    }
}
