package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public class Var implements Expr {
    String var;

    public Var(String var) {
        this.var = var;
    }

    public EvalResult eval(Map<String, Expr> vars) {
        return vars.get(var).eval(vars);
    }
}
