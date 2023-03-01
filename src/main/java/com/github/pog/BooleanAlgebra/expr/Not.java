package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class Not implements Expr {
    Expr e;

    public Not(Expr e) {
        this.e = e;
    }

    public EvalResult eval(Map<String, Expr> m) {
        EvalResult r = e.eval(m);
        return new EvalResult(!r.result, r.vars);
    }
}
