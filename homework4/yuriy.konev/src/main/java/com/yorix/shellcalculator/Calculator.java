package com.yorix.shellcalculator;

import com.yorix.shellcalculator.operations.Operation;
import com.yorix.shellcalculator.operations.OperationsList;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class Calculator {
    private final OperationsList operationsList;
    private final ArrayDeque<Operand> operandStack;
    private final ArrayDeque<Token> operationStack;

    public Calculator(OperationsList operationsList) {
        this.operationsList = operationsList;
        operandStack = new ArrayDeque<>();
        operationStack = new ArrayDeque<>();
    }

    double calculate(ArrayList<Token> tokens) {
        operandStack.clear();
        operationStack.clear();

        for (Token token : tokens) {
            if (token.isOperand())
                operandStack.push(new Operand(Double.parseDouble(token.getValue())));
            else if (token.isOpenBracket())
                operationStack.push(token);
            else if (token.isCloseBracket()) {
                while (!operationStack.peek().isOpenBracket()) {
                    executeOperation();
                }
                operationStack.pop();
            } else if (token.isOperator()) {
                while (!operationStack.isEmpty() && operationStack.peek().isOperator() && higherPriority(token)) {
                    executeOperation();
                }
                operationStack.push(token);
            }
        }

        while (!operationStack.isEmpty()) {
            executeOperation();
        }
        return operandStack.pop().getValue();
    }

    private boolean higherPriority(Token token) {
        int currentPriority = operationsList.find(token).priority();
        int previousPiority = operationsList.find(operationStack.peek()).priority();
        return previousPiority >= currentPriority;
    }

    private void executeOperation() {
        Operation operation = operationsList.find(operationStack.pop());
        operation.execute(operandStack);
    }
}
