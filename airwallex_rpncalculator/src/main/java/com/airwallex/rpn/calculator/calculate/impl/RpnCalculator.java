package com.airwallex.rpn.calculator.calculate.impl;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.factory.OperatorFactory;
import com.airwallex.rpn.calculator.calculate.Calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author : yangzanjie
 * @description: Achieves rpn calculator.
 */
public class RpnCalculator implements Calculator {

    /**
     * Strings containing whitespace separated lists of numbers and operators.
     */
    private static final String DELIMITER = " ";

    /**
     * Displayed to 10 decimal places (or less if it causes no loss of precision).
     */
    private static final String DECIMAL_FORMAT = "0.##########";
    /**
     * Numbers are stored on the stack to 15 decimal places of precision.
     */
    private static final int DATA_SCALE = 15;

    /**
     * Data stack.
     */
    private Deque<BigDecimal> dataStack = new ArrayDeque<>();

    /**
     * Tracker calculator process, help undo operator.
     */
    private List<RpnCalculatorTracker> rpnCalculatorTrackerList = new LinkedList<>();

    @Override
    public void calculate(String input) throws CalculatorException {
        if (null == input || input.isEmpty())
            throw new CalculatorException(input, "insufficient parameters. " +
                    "Input can't be empty, please read readme.md for details.");
        int position = 0;
        for (String str : parseInput(input, DELIMITER)) {
            position++;
            OperatorFactory.getOperationInstance(str.toUpperCase(), position)
                    .execute(dataStack, rpnCalculatorTrackerList, position);
        }
    }

    @Override
    public void display() {
        System.out.print("stack:");
        DecimalFormat format = new DecimalFormat(DECIMAL_FORMAT);
        format.setRoundingMode(RoundingMode.DOWN);
        dataStack.descendingIterator().forEachRemaining(value -> {
            System.out.print(" ");
            System.out.print(format.format(value.doubleValue()));
        });
        System.out.println();
    }

    /**
     * Parses the string input through a delimiter passed
     *
     * @param input
     * @param delimiter
     * @return
     */
    public static String[] parseInput(String input, String delimiter) {
        return input.split(delimiter);
    }

    public void setDataStack(Deque<BigDecimal> dataStack) {
        this.dataStack = dataStack;
    }

    public static int getDataScale() {
        return DATA_SCALE;
    }
}
