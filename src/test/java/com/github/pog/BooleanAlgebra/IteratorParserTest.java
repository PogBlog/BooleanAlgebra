package com.github.pog.BooleanAlgebra;
import com.github.pog.BooleanAlgebra.expr.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.Optional;

import org.junit.Test;

public class IteratorParserTest {
    @Test
    public void testParseTermT() {
        LexerIterator it = new LexerIterator("T");
        assertThat(IteratorParser.parseTerminal(it).get(), instanceOf(True.class)); 
    }

    @Test
    public void testParseTermF() {
        LexerIterator it = new LexerIterator("F");
        assertThat(IteratorParser.parseTerminal(it).get(), instanceOf(False.class)); 
    }

    @Test
    public void testParseTermFailure() {
        LexerIterator it = new LexerIterator("!!!!");
        assertThat(IteratorParser.parseTerminal(it), is(Optional.empty()));
    }

    @Test
    public void testParseTermNotT() {
        LexerIterator it = new LexerIterator("!T");
        assertThat(IteratorParser.parseNotExpr(it).get(), instanceOf(Not.class)); 
    }

    @Test
    public void testParseTermNotNotT() {
        LexerIterator it = new LexerIterator("!!T");
        assertThat(IteratorParser.parseNotExpr(it).get(), instanceOf(Not.class)); 
    }

    @Test
    public void testParseTermNotTerm() {
        LexerIterator it = new LexerIterator("T");
        assertThat(IteratorParser.parseNotExpr(it).get(), instanceOf(True.class)); 
    }

    @Test
    public void testParseTermNotFailure() {
        LexerIterator it = new LexerIterator("&&");
        assertThat(IteratorParser.parseNotExpr(it), is(Optional.empty()));
    }
}
