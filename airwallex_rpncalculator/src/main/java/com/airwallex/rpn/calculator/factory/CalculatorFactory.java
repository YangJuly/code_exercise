package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.calculate.Calculator;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculator;

/**
 * @author : yangzanjie
 * @description: Calculator factory, generate Calculator.
 */
public class CalculatorFactory {
    private static RpnCalculator rpnCalculator = null;

    /**
     * Genrate new instance of RpnCalculator
     * @return
     */
    public static Calculator getCalculator() {
        if (rpnCalculator == null)
            rpnCalculator = new RpnCalculator();
        return rpnCalculator;
    }
}
