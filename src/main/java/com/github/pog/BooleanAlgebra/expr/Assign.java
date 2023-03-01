package com.github.pog.BooleanAlgebra.expr;

import java.util.HashMap;
import java.util.Map;

public class Assign implements Expr {
    String var;
    Expr value;

    public Assign(String var, Expr value) {
        this.var = var;
        this.value = value;
    }

    public EvalResult eval(Map<String, Expr> m) {
        EvalResult r = value.eval(m);
        Map<String, Expr> m2 = new HashMap<>(r.vars);
        m2.put(var, r.result ? new True() : new False());
        return new EvalResult(r.result, m2);
    }
}
