package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class EvalResult {
    public Boolean result;
    public Map<String, Expr> vars;

    public EvalResult(Boolean result, Map<String, Expr> vars) {
        this.result = result;
        this.vars = vars;
    }
}
