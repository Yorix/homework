package com.yorix.shellcalculator;

public class Operand {
    private double value;

    Operand(double value) {
        this.value = value;
    }

    double getValue() {
        return value;
    }

    public Operand add(Operand other) {
        return new Operand(this.value + other.value);
    }

    public Operand subtract(Operand other) {
        return new Operand(this.value - other.value);
    }

    public Operand multiply(Operand other) {
        return new Operand(this.value * other.value);
    }

    public Operand divide(Operand other) {
        return new Operand(this.value / other.value);
    }
}
