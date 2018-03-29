package com.lixin.symbols;

import com.lixin.inter.Id;
import com.lixin.lexer.Token;

import java.util.Hashtable;

/**
 * @author lixin
 */
public class Env {
    private Hashtable hashtable;
    protected Env prev;

    public Env(Env env) {
        hashtable = new Hashtable();
        prev = env;
    }

    public void put(Token word, Id id) {
        hashtable.put(word, id);
    }

    public Id get(Token word) {
        for (Env env = this; env != null; env = env.prev) {
            Id found = (Id) (env.hashtable.get(word));
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
