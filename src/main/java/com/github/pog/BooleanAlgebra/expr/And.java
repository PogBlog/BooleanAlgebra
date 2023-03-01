package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class And extends BinaryOp {
    public And(Expr e1, Expr e2) {
        super(e1, e2);
    }
    public EvalResult eval(Map<String, Expr> m) {
        EvalResult r1 = e1.eval(m);
        EvalResult r2 = e2.eval(r1.vars);
        return new EvalResult(r1.result && r2.result, r2.vars);
    }
}
