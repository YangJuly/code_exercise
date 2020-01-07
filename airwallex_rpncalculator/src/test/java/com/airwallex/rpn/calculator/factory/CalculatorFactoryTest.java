package com.airwallex.rpn.calculator.factory;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author : yangzanjie
 * @description: test calculator
 */
public class CalculatorFactoryTest {
    @Test
    public void testGetCalculator() {
        assertNotNull(CalculatorFactory.getCalculator());
    }
}
