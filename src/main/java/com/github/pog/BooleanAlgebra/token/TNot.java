package com.github.pog.BooleanAlgebra.token;

public class TNot extends Token {
    @Override
    TokenType getType() {
        return Token.TokenType.Not;
    }
}
