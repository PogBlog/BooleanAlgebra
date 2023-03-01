/**
 * Token abstract base class for lexing a string into a sequence of tokens.
 * None of these tokens actually contain any data aside from Var; if it weren't
 * for Var, we'd be able to encode this as an enum rather than an OOP inheritance
 * approach. Alas, we have one token that actually needs to contain extra data.
 */

package com.github.pog.BooleanAlgebra.token;

public abstract class Token {
    enum TokenType {
        And,
        Equals,
        False,
        LParen,
        NoOp,
        Not,
        Or,
        RParen,
        True,
        Var
    };

    abstract TokenType getType();
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Token) {
            return getType().equals(((Token) obj).getType());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getType().ordinal();
    }
}
