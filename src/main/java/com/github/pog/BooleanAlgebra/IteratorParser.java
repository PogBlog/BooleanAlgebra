/**
 * Converts an Iterator of Tokens into an Expr.
 * 
 * Our grammar is as follows. See grammar.md for analysis.
 * 
 * S ::= OR_EXPR
 * OR_EXPR ::= AND_EXPR OR_EXPR2
 * OR_EXPR2 ::= || AND_EXPR OR_EXPR2 | epsilon
 * AND_EXPR ::= NOT_EXPR AND_EXPR2
 * AND_EXPR2 ::= && NOT_EXPR AND_EXPR2 | epsilon
 * NOT_EXPR ::= ! NOT_EXPR | PAREN_EXPR
 * PAREN_EXPR ::= (OR_EXPR) | TERMINAL
 * TERMINAL ::= T | F
 * 
 * Grammar specified, all that's left is to define methods for each of these
 * generators. Only `S` has to be public; the rest are internal, but
 * are currently public for ease of examination and testing.
 * 
 * Each of these parsing functions takes an Iterator<Token> and returns an
 * Optional<Expr>. The iterator consumes 0 or more tokens along the way.
 * If the parser fails, the position of the iterator might be helpful
 * to debug!
 */

package com.github.pog.BooleanAlgebra;
import com.github.pog.BooleanAlgebra.expr.*;

import java.util.Optional;
import java.util.Map;

public class IteratorParser {
    public static Optional<Expr> parseS(LexerIterator it) {
        return parseOrExpr(it);
    }

    public static Optional<Expr> parseOrExpr(LexerIterator it) {
        Optional<Expr> e1_opt = parseAndExpr(it);
        return e1_opt.flatMap((Expr e1) -> parseOrExpr2(e1, it));
    }

    public static Optional<Expr> parseOrExpr2(Expr e1, LexerIterator it) {
        if (containsNextToken(it, Token.Or)) {
            it.next();
            return parseAndExpr(it).flatMap((Expr e2) -> parseOrExpr2(new Or(e1, e2), it));
        }
        return Optional.of(e1);
    }

    public static Optional<Expr> parseAndExpr(LexerIterator it) {
        Optional<Expr> e1_opt = parseNotExpr(it);
        return e1_opt.flatMap((Expr e1) -> parseAndExpr2(e1, it));
    }

    public static Optional<Expr> parseAndExpr2(Expr e1, LexerIterator it) {
        if (containsNextToken(it, Token.And)) {
            it.next();
            return parseAndExpr(it).flatMap((Expr e2) -> parseOrExpr2(new And(e1, e2), it));
        }
        return Optional.of(e1);
    }

    public static Optional<Expr> parseNotExpr(LexerIterator it) {
        if (containsNextToken(it, Token.Not)) {
            it.next();
            return parseNotExpr(it).map((Expr e) -> new Not(e)); 
        }
        return parseParenExpr(it);
    }

    public static Optional<Expr> parseParenExpr(LexerIterator it) {
        if (containsNextToken(it, Token.LParen)) {
            it.next();
            Optional<Expr> e = parseOrExpr(it);
            if (e.isPresent() && containsNextToken(it, Token.RParen)) {
                it.next();
                return e;
            }
            return Optional.empty();
        }
        return parseTerminal(it);
    }

    public static Optional<Expr> parseTerminal(LexerIterator it) {
        if (containsNextToken(it, Token.True)) {
            it.next();
            return Optional.of(new True());
        }
        if (containsNextToken(it, Token.False)) {
            it.next();
            return Optional.of(new False());
        }
        return Optional.empty();
    }

    static boolean containsNextToken(LexerIterator it, Token expected) {
        return it.nextOpt()
                 .map(Map.Entry::getValue)
                 .filter((t) -> t.equals(expected))
                 .isPresent();
    }
}
