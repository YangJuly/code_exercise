package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.operations.Operator;
import com.airwallex.rpn.calculator.operations.impl.RpnOperator;

/**
 * @author : yangzanjie
 * @description: Operator factory, generate operator.
 */
public class OperatorFactory {

    /**
     * Generate the Operator instance which implemented using Lambda expression.
     *
     * @param operation
     * @return
     */
    public static Operator getOperationInstance(String operation, int position) throws CalculatorException {
        return RpnOperator.getRpnOperator(operation, position);
    }
}
