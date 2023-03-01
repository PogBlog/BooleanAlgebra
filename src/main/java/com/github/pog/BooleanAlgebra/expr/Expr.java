/**
 * Interface for evaluating a Boolean expression.
 */

package com.github.pog.BooleanAlgebra.expr;

import java.util.Map;

public interface Expr {
    public EvalResult eval(Map<String, Expr> vars);
}