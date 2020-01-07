package com.airwallex.rpn.calculator.calculate;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * @author : yangzanjie
 * @description: self test
 */
public class RpnCalculatorTest {
    OutputStream os;

    @Before
    public void setup() {
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
    }


    /*** Test normal ***/

    @Test
    public void testAddOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "5 2 +";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 7\n", os.toString());
    }

    @Test
    public void testSubtractOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "10000 1000 -";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 9000\n", os.toString());
    }

    @Test
    public void testMultiplyOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "10 99 *";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 990\n", os.toString());
    }

    @Test
    public void testDivideOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "99 9 /";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 11\n", os.toString());
    }

    @Test
    public void testSqrtOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "16 sqrt";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 4\n", os.toString());
    }

    @Test
    public void testClearOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "1 2 3 4";
        calculator.calculate(data);
        calculator.display();
        data = "clear";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 1 2 3 4\n" +
                "stack:\n", os.toString());
    }

    @Test
    public void testUndoOperators() {
        Calculator calculator = new RpnCalculator();
        String data = "1 2 3 4";
        calculator.calculate(data);
        calculator.display();
        data = "undo undo";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 1 2 3 4\n" +
                "stack: 1 2\n", os.toString());
    }


    /*** Test exception ***/

    @Test(expected = CalculatorException.class)
    public void testNoSpacesOne() throws CalculatorException {
        Calculator calculator = new RpnCalculator();
        String data = "12+";
        calculator.calculate(data);
    }

    @Test(expected = CalculatorException.class)
    public void testNoSpacesTwo() throws CalculatorException {
        Calculator calculator = new RpnCalculator();
        String data = "1 12+";
        calculator.calculate(data);
    }

    @Test(expected = CalculatorException.class)
    public void testDivideZero() throws CalculatorException {
        Calculator calculator = new RpnCalculator();
        String data = "10 0 /";
        calculator.calculate(data);
    }

    @Test(expected = CalculatorException.class)
    public void testInputNull() throws CalculatorException {
        Calculator calculator = new RpnCalculator();
        String data = null;
        calculator.calculate(data);
    }

    @Test()
    public void testInputLongNum() throws CalculatorException {
        Calculator calculator = new RpnCalculator();
        String data = "123456789.12345667879";
        calculator.calculate(data);
    }
}
