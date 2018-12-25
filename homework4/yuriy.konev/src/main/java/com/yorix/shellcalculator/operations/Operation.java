package com.yorix.shellcalculator.operations;

import com.yorix.shellcalculator.Operand;

import java.util.ArrayDeque;

public interface Operation {
    void execute(ArrayDeque<Operand> operands);
    int priority();
    String getValue();
}
