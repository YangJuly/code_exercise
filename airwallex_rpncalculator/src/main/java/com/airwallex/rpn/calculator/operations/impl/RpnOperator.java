package com.airwallex.rpn.calculator.operations.impl;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculator;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculatorTracker;
import com.airwallex.rpn.calculator.operations.Operator;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;

/**
 * @author : yangzanjie
 * @description: Provide rpn operator.
 */
public class RpnOperator {

    private static final String NUMBER_REGEX = "-?\\d+(\\.\\d+)?";

    /**
     * Checks if a String is a numeric value
     *
     * @param inputString
     * @return
     */
    public static boolean isNumeric(String inputString) {
        return inputString.matches(NUMBER_REGEX);
    }

    /**
     * Updates tracker after each operation
     *
     * @param numberStack
     * @param rpnCalculatorTrackerList
     */
    public static void updateTracker(Deque<BigDecimal> numberStack, List<RpnCalculatorTracker> rpnCalculatorTrackerList) {
        RpnCalculatorTracker rpnCalculatorTracker = new RpnCalculatorTracker();
        numberStack.forEach(element -> rpnCalculatorTracker.addToTracker(element));
        rpnCalculatorTrackerList.add(rpnCalculatorTracker);
    }

    /**
     * Checks the stack for correct operation
     *
     * @param stack
     * @param count
     * @param operator
     * @throws CalculatorException
     */
    public static void checkStack(Deque<BigDecimal> stack, int count, String operator) throws CalculatorException {
        if (stack.isEmpty())
            throw new CalculatorException("Stack is empty, insufficient parameters");
        if (stack.size() == 1 && !operator.equalsIgnoreCase(RpnOperatorType.SQRT.name()))
            throw new CalculatorException(operator + " (position: " + count + "): insufficient parameters");
    }

    /**
     * Get rpn operator
     *
     * @param operation
     * @param position
     * @return
     * @throws CalculatorException
     */
    public static Operator getRpnOperator(String operation, int position) throws CalculatorException {
        if (isNumeric(operation)) {
            return ((numberStack, operationList, pos) -> {
                numberStack.push(new BigDecimal(operation).setScale(RpnCalculator.getDataScale(),
                        BigDecimal.ROUND_HALF_UP));
                updateTracker(numberStack, operationList);
            });
        } else {
            RpnOperatorType rpnOperatorType = RpnOperatorType.SUPPORTED_OPERATIONS.get(operation);
            if (rpnOperatorType == null) throw new CalculatorException(operation, position);
            return rpnOperatorType.getOperator();
        }
    }
}
