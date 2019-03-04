package com.yorix.shellcalculator;

import com.yorix.shellcalculator.operations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Calculator calculator() {
        return new Calculator(operationsList());
    }

    @Bean
    public OperationsList operationsList() {
        return new OperationsList(addition(), subtract(), multiply(), divide());
    }

    @Bean
    public Addition addition() {
        return new Addition();
    }

    @Bean
    public Subtract subtract() {
        return new Subtract();
    }

    @Bean
    public Multiply multiply() {
        return new Multiply();
    }

    @Bean
    public Divide divide() {
        return new Divide();
    }
}
