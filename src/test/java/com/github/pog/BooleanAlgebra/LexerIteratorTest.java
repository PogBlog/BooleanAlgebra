package com.github.pog.BooleanAlgebra;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

public class LexerIteratorTest {
    static <T> List<T> iteratorToList(Iterator<T> it) {
        Iterable<T> i = () -> it;
        return StreamSupport.stream(i.spliterator(), false).collect(Collectors.toList());
    }

    @Test
    public void testLexerSimple() {
        assertThat(
            List.of(Token.True).toArray(), 
            is(iteratorToList(new LexerIterator("T")).toArray()));
    }

    @Test
    public void testLexerHarder() {
        assertThat(
            List.of(Token.True, Token.And, Token.False, Token.Or, Token.Not, Token.True).toArray(), 
            is(iteratorToList(new LexerIterator("T && F || !T")).toArray()));
    }

    @Test
    public void testLexerWithParens() {
        assertThat(
            List.of(Token.True, Token.And, Token.LParen, Token.False, Token.Or, Token.Not, Token.True, Token.RParen).toArray(), 
            is(iteratorToList(new LexerIterator("T && (F || !T)")).toArray()));
    }
}
