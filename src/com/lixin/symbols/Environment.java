package com.lixin.symbols;

import com.lixin.interpreter.Identifier;
import com.lixin.lexer.Token;

import java.util.Hashtable;

/**
 * @author lixin
 */
public class Environment {
    private Hashtable hashtable;
    protected Environment previousEnvironment;

    public Environment(Environment environment) {
        hashtable = new Hashtable();
        previousEnvironment = environment;
    }

    public void put(Token word, Identifier identifier) {
        hashtable.put(word, identifier);
    }

    public Identifier get(Token word) {
        for (Environment environment = this; environment != null; environment = environment.previousEnvironment) {
            Identifier found = (Identifier) (environment.hashtable.get(word));
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
