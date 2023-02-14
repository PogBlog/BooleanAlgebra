package com.github.pog.BooleanAlgebra.expr;

public abstract class BinaryOp implements Expr {
    Expr e1, e2;

    public BinaryOp(Expr e1, Expr e2) {
        this.e1 = e1;
        this.e2 = e2;
    }
}
