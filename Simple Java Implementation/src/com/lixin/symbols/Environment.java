package com.lixin.symbols;

import com.lixin.interpreter.Identifier;
import com.lixin.lexer.Token;

import java.util.Hashtable;

/**
 * @author lixin
 */
public class Environment {
    private Hashtable<Token, Identifier> table;
    private Environment previousEnvironment;

    public Environment(Environment environment) {
        table = new Hashtable<>();
        previousEnvironment = environment;
    }

    public void put(Token word, Identifier identifier) {
        table.put(word, identifier);
    }

    public Identifier get(Token word) {
        for (Environment environment = this; environment != null; environment = environment.previousEnvironment) {
            Identifier found = (environment.table.get(word));
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
