package com.github.pog.BooleanAlgebra.token;

public class TNoOp extends Token {
    @Override
    TokenType getType() {
        return Token.TokenType.NoOp;
    }
}
