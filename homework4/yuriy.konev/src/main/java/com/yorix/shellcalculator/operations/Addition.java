package com.yorix.shellcalculator.operations;

import com.yorix.shellcalculator.Operand;

import java.util.ArrayDeque;

public class Addition implements Operation {

    @Override
    public void execute(ArrayDeque<Operand> operands) {
        Operand right = operands.pop();
        Operand left = operands.pop();
        operands.push(left.add(right));
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public String getValue() {
        return "+";
    }
}
