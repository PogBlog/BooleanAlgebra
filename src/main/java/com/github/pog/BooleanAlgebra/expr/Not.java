package com.github.pog.BooleanAlgebra.expr;

public class Not implements Expr {
    Expr e;

    public Not(Expr e) {
        this.e = e;
    }

    public boolean eval() {
        return !e.eval();
    }
}
