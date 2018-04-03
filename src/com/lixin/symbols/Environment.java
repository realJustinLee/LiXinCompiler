package com.lixin.symbols;

import com.lixin.inter.Id;
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

    public void put(Token word, Id id) {
        hashtable.put(word, id);
    }

    public Id get(Token word) {
        for (Environment environment = this; environment != null; environment = environment.previousEnvironment) {
            Id found = (Id) (environment.hashtable.get(word));
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
