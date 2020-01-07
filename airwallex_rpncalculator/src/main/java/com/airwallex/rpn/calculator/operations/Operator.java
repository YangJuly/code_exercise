package com.airwallex.rpn.calculator.operations;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculatorTracker;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.List;

/**
 * @author : yangzanjie
 * @description: Execute specific operations
 */
public interface Operator {
    /**
     * @param numberStack
     * @param rpnCalculatorTrackerList
     * @param position
     * @throws CalculatorException
     */
    void execute(Deque<BigDecimal> numberStack,
                 List<RpnCalculatorTracker> rpnCalculatorTrackerList,
                 int position) throws CalculatorException;

}
