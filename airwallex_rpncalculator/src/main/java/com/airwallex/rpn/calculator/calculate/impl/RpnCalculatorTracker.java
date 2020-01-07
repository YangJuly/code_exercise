package com.airwallex.rpn.calculator.calculate.impl;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author : yangzanjie
 * @description: Record rpn calculator process, help undo operator.
 */
public class RpnCalculatorTracker {

    Deque<BigDecimal> tracker = new ArrayDeque<>();

    public void addToTracker(BigDecimal number) {
        tracker.push(number);
    }

    public Deque<BigDecimal> getTracker() {
        return tracker;
    }
}
