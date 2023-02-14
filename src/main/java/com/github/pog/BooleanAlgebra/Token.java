/**
 * Token enum for lexing our Boolean algebra. This is _extremely_ simple,
 * since every single one of our tokens is self-evident and contains no
 * additional data the way that, say, an arithmetic lexer would have to store
 * integer data along with the fact that it just lexed an integer.
 * A more complicated lexing system will likely make `Token` an abstract
 * base class rather than an `enum`, and each specific token a subclass
 * of that abstract base class (see the `expr` folder for an example).
 * 
 * This isn't actually needed; because of the above, we could consider a string
 * to already be lexed. However, it's good practice.
 */

package com.github.pog.BooleanAlgebra;

/**
 * All of the different tokens in our Boolean algebra system. They are:
 * * The atoms (T and F)
 * * The operators (&&, ||, and !)
 * * Left and right parens
 * 
 */
public enum Token {
    True,
    False,
    And,
    Or,
    Not,
    LParen,
    RParen,
    NoOp
}
