/**
 * The `LexerIterator` class implements `Iterator<Token>`, which means
 * that it lazily pulls tokens from a string.
 * This is because I have a gigantic boner for lazy evaluation. It is
 * totally reasonable to slurp the whole darn thing all at once into
 * a data structure, but my brain has been fried by too much Haskell.
 * 
 * With an iterator, you can slurp the thing into a data structure if
 * you want... or you can keep it lazy. It's up to you!
 */

package com.github.pog.BooleanAlgebra;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

public class LexerIterator implements Iterator<Token> {
    static final Map<String, Token> map = Map.of(
        "T", Token.True,
        "F", Token.False,
        "&&", Token.And,
        "||", Token.Or,
        "!", Token.Not,
        "(", Token.LParen,
        ")", Token.RParen,
        " ", Token.NoOp,
        "\t", Token.NoOp
    );

    int _position;
    String _s;

    public LexerIterator(String s) {
        this._position = 0;
        this._s = s;
    }

    LexerIterator(String s, int position) {
        this._position = position;
        this._s = s;
    }

    public Optional<Map.Entry<String, Token>> nextOpt() {
        Optional<Map.Entry<String, Token>> entry;
        do {
            entry = map.entrySet()
                       .stream()
                       .filter((e) -> _s.startsWith(e.getKey(), _position))
                       .findAny();
            if (entry.isPresent() && entry.get().getValue() == Token.NoOp) {
                _position += entry.get().getKey().length();
            }
        } while(entry.isPresent() && entry.get().getValue() == Token.NoOp);
        return entry;
    }

    public boolean hasNext() {
        return nextOpt().isPresent();
    }

    public Token next() throws NoSuchElementException {
        try {
            Map.Entry<String, Token> e = nextOpt().get();
            _position += e.getKey().length();
            return e.getValue();
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException(String.format("Lexer error at position %d", _position));
        }
    }

    public LexerIterator clone() {
        return new LexerIterator(_s, _position);
    }

    public int getPosition() {
        return _position;
    }

    public void setPosition(int position) {
        _position = position;
    }
}
