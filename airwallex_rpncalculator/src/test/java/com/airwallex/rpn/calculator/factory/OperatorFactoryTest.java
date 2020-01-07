package com.airwallex.rpn.calculator.factory;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.operations.Operator;
import org.junit.Test;
import org.mockito.Matchers;

import static org.junit.Assert.assertTrue;

/**
 * @author : yangzanjie
 * @description: test operation factory
 */
public class OperatorFactoryTest {
    @Test
    public void testGetAddOperation() {
        assertTrue(OperatorFactory.getOperationInstance("+", 1) instanceof Operator);
    }

    @Test
    public void testGetSubtractOperation() {
        assertTrue(OperatorFactory.getOperationInstance("-", 1) instanceof Operator);
    }

    @Test
    public void testGetSqrtOperation() {
        assertTrue(OperatorFactory.getOperationInstance("SQRT", 1) instanceof Operator);
    }

    @Test
    public void testGetDivideOperation() {
        assertTrue(OperatorFactory.getOperationInstance("/", 1) instanceof Operator);
    }

    @Test
    public void testGetMultiplyOperation() {
        assertTrue(OperatorFactory.getOperationInstance("*", 1) instanceof Operator);
    }

    @Test
    public void testGetClearOperation() {
        assertTrue(OperatorFactory.getOperationInstance("CLEAR", 1) instanceof Operator);
    }

    @Test
    public void testGetUndoOperation() {
        assertTrue(OperatorFactory.getOperationInstance("UNDO", 1) instanceof Operator);
    }

    @Test(expected = CalculatorException.class)
    public void testThrowCalculatorException() throws CalculatorException {
        OperatorFactory.getOperationInstance("&&", 1)
                .execute(Matchers.anyObject(),
                        Matchers.anyObject(),
                        Matchers.anyInt());
    }
}
