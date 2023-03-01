package com.github.pog.BooleanAlgebra;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.HashMap;

import com.github.pog.BooleanAlgebra.expr.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static final String prompt = "Input a Boolean expression: ";
    public static void main( String[] args ) throws IOException
    {
        Map<String, Expr> vars = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        do {
            System.out.print(prompt);
            line = br.readLine();
            if (line != null) {
                Optional<Expr> opt = IteratorParser.parseS(new LexerIterator(line));
                if (opt.isPresent()) {
                    EvalResult r = opt.get().eval(vars);
                    System.out.println(r.result);
                    vars = r.vars;
                }
            }
        } while(line != null);
        System.out.println("Ceterum censeo Carthaginam delenda est.");
    }
}
