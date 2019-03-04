package com.yorix.shellcalculator.operations;

import com.yorix.shellcalculator.Operand;

import java.util.ArrayDeque;

public class Multiply implements Operation {
    @Override
    public void execute(ArrayDeque<Operand> operands) {
        Operand right = operands.pop();
        Operand left = operands.pop();
        operands.push(left.multiply(right));
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public String getValue() {
        return "*";
    }
}
