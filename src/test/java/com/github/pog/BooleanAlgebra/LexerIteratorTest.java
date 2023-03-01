package com.github.pog.BooleanAlgebra;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

import com.github.pog.BooleanAlgebra.token.*;

public class LexerIteratorTest {
    static <T> List<T> iteratorToList(Iterator<T> it) {
        Iterable<T> i = () -> it;
        return StreamSupport.stream(i.spliterator(), false).collect(Collectors.toList());
    }

    @Test
    public void testLexerSimple() {
        assertThat(
            List.of(new TTrue()).toArray(), 
            is(iteratorToList(new LexerIterator("T")).toArray()));
    }

    @Test
    public void testLexerHarder() {
        assertThat(
            List.of(new TTrue(), new TAnd(), new TFalse(), new TOr(), new TNot(), new TTrue()).toArray(), 
            is(iteratorToList(new LexerIterator("T && F || !T")).toArray()));
    }

    @Test
    public void testLexerWithParens() {
        assertThat(
            List.of(new TTrue(), new TAnd(), new TLParen(), new TFalse(), new TOr(), new TNot(), new TTrue(), new TRParen()).toArray(), 
            is(iteratorToList(new LexerIterator("T && (F || !T)")).toArray()));
    }
}
