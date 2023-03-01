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
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pog.BooleanAlgebra.token.*;

public class LexerIterator implements Iterator<Token> {
    static class RegexEntry {
        public Pattern p;
        public Function<Matcher, Token> f;

        public RegexEntry(Pattern p, Function<Matcher, Token> f) {
            this.p = p;
            this.f = f;
        }
    }

    static final List<RegexEntry> map = List.of(
        new RegexEntry(Pattern.compile("T"), (ignored) -> new TTrue()),
        new RegexEntry(Pattern.compile("F"), (ignored) -> new TFalse()),
        new RegexEntry(Pattern.compile("&&"), (ignored) -> new TAnd()),
        new RegexEntry(Pattern.compile("\\|\\|"), (ignored) -> new TOr()),
        new RegexEntry(Pattern.compile("!"), (ignored) -> new TNot()),
        new RegexEntry(Pattern.compile("\\("), (ignored) -> new TLParen()),
        new RegexEntry(Pattern.compile("\\)"), (ignored) -> new TRParen()),
        new RegexEntry(Pattern.compile("\\s+"), (ignored) -> new TNoOp()),
        new RegexEntry(Pattern.compile("\\w+"), (m) -> new TVar(m.group(0))),
        new RegexEntry(Pattern.compile("="), (ignored) -> new TEquals())
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

    class MatchResult {
        public Matcher m;
        public Function<Matcher, Token> f;
        public MatchResult(Matcher m, Function<Matcher, Token> f) {
            this.m = m;
            this.f = f;
        }
        public Token apply() {
            return f.apply(m);
        }
    }

    public Optional<MatchResult> nextOptMatch() {
        boolean foundToken;
        do {
            foundToken = false;
            for(var e : map) {
                Pattern p = e.p;
                Function<Matcher, Token> f = e.f;
                Matcher m = p.matcher(_s).region(_position, _s.length());
                if (m.lookingAt()) {
                    foundToken = true;
                    Token t = f.apply(m);
                    if (!(t instanceof TNoOp)) {
                        return Optional.of(new MatchResult(m, f));
                    } else {
                        _position += m.group(0).length();
                        break;
                    }
                }
            }
        } while(foundToken && _position < _s.length());
        return Optional.empty();
    }

    public Optional<Token> nextOpt() {
        return nextOptMatch().map((m) -> m.apply());
    }

    public boolean hasNext() {
        return nextOpt().isPresent();
    }

    public Token next() throws NoSuchElementException {
        try {
            MatchResult m = nextOptMatch().get();
            _position += m.m.group(0).length();
            return m.apply();
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
