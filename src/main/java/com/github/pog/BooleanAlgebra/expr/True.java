package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class True implements Expr {
    public EvalResult eval(Map<String, Expr> m) {
        return new EvalResult(true, m);
    }
}
