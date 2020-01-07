package com.airwallex.rpn.calculator.exception;

/**
 * @author : yangzanjie
 * @description: Calculation exception.
 */
public class CalculatorException extends RuntimeException {
    public CalculatorException(String op) {
        super(String.format("Invalid operation %1$s", op));
    }

    public CalculatorException(String op, String message) {
        super(String.format("Invalid operator %1$s : %2$s", op, message));
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculatorException(String op, int pos) {
        super(String.format("Invalid operator %1$s (position: %2$d): insufficient parameters.", op, pos));
    }
}
