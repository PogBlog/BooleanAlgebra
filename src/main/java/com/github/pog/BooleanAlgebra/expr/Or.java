package com.github.pog.BooleanAlgebra.expr;

public class Or extends BinaryOp {
    public Or(Expr e1, Expr e2) {
        super(e1, e2);
    }
    public boolean eval() {
        return e1.eval() || e2.eval();
    }
}
