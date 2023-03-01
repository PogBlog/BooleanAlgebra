package com.github.pog.BooleanAlgebra.token;

public class TVar extends Token {
    public String label;
    public TVar(String label) {
        this.label = label;
    }
    @Override
    TokenType getType() {
        return Token.TokenType.Var;
    }
    @Override
    public int hashCode() {
        return 31 * label.hashCode() + getType().ordinal();
    }
}
