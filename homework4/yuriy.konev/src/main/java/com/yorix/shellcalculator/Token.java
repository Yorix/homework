package com.yorix.shellcalculator;

public class Token {
    private String value;

    Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    boolean isOperand() {
        try {
            Double.parseDouble(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean isOperator() {
        return "+-*/".contains(value);
    }

    boolean isOpenBracket() {
        return value.equals("(");
    }

    boolean isCloseBracket() {
        return value.equals(")");
    }
}
