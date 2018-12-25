package com.yorix.shellcalculator.operations;

import com.yorix.shellcalculator.Token;

import java.util.Arrays;

public class OperationsList {
    private Operation[] operations;

    public OperationsList(Operation... operations) {
        this.operations = operations;
    }

    public Operation find(Token token) {
        return Arrays.stream(operations)
                .filter(operation -> operation.getValue().equals(token.getValue()))
                .findFirst().get();
    }
}
