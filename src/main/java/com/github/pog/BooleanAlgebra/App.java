package com.github.pog.BooleanAlgebra;

import java.io.InputStreamReader;
import java.io.BufferedReader;

/**
 * Hello world!
 *
 */
public class App 
{

    static final String prompt = "Input a Boolean expression: ";
    public static void main( String[] args )
    {
        System.out.print(prompt);

        new BufferedReader(new InputStreamReader(System.in)).lines()
            .map((s) -> IteratorParser.parseS(new LexerIterator(s)))
            .forEach((opt) -> {
                opt.ifPresent((e) -> System.out.println(e.eval()));
                if (opt.isEmpty()) {
                    System.out.println("Parse error!");
                }
                System.out.print(prompt);
            });
    }
}
