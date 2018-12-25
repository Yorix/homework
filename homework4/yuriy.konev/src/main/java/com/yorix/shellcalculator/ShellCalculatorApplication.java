package com.yorix.shellcalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.ArrayList;
import java.util.StringTokenizer;

@ShellComponent
@SpringBootApplication(scanBasePackages = "com.yorix.shellcalculator")
public class ShellCalculatorApplication {

    private final Calculator calculator;

    @Autowired
    public ShellCalculatorApplication(Calculator calculator) {
        this.calculator = calculator;
    }

    @ShellMethod(value = "Expression evaluation", key = "?")
    public double calc(String expression) {
        System.out.print("\r");

        ArrayList<Token> tokens = tokenize(expression);
        return calculator.calculate(tokens);
    }

    private ArrayList<Token> tokenize(String expression) {
        expression = expression.replace(" ", "").replace("(-", "(0-");
        if (expression.charAt(0) == '-') expression = "0" + expression;
        StringTokenizer tokenizer = new StringTokenizer(expression, "+-*/()", true);
        ArrayList<Token> tokens = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            tokens.add(new Token(tokenizer.nextToken()));
        }
        return tokens;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShellCalculatorApplication.class, args);
    }
}
