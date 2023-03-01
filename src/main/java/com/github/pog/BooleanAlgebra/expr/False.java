package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class False implements Expr {
    public EvalResult eval(Map<String, Expr> m) {
        return new EvalResult(false, m);
    }
}
