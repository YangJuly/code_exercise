package com.airwallex.rpn.calculator.calculate;

import com.airwallex.rpn.calculator.exception.CalculatorException;

/**
 * @author : yangzanjie
 * @description: Execute specific calculator.
 */
public interface Calculator {
    /**
     * Run calculator
     * @param input
     * @throws CalculatorException
     */
    void calculate(String input) throws CalculatorException;

    /**
     * Display content of stack
     */
    void display();
}
