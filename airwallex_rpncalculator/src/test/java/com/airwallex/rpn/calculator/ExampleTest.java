package com.airwallex.rpn.calculator;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.calculate.Calculator;
import com.airwallex.rpn.calculator.calculate.impl.RpnCalculator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author : yangzanjie
 * @description: test example
 */
public class ExampleTest {

    OutputStream os;

    @Before
    public void setup() {
        os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
    }

    @Test
    public void example1Test() {
        Calculator calculator = new RpnCalculator();
        String data = "5 2";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 5 2\n", os.toString());
    }

    @Test
    public void example2Test() {
        Calculator calculator = new RpnCalculator();
        String data = "2 sqrt";
        calculator.calculate(data);
        calculator.display();
        data = "clear 9 sqrt";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 1.4142135623\n" +
                "stack: 3\n", os.toString());
    }

    @Test
    public void example3Test() {
        Calculator calculator = new RpnCalculator();
        String data = "5 2 -";
        calculator.calculate(data);
        calculator.display();
        data = "clear";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 3\n" +
                "stack:\n", os.toString());
    }

    @Test
    public void example4Test() {
        Calculator calculator = new RpnCalculator();
        String data = "5 4 3 2";
        calculator.calculate(data);
        calculator.display();
        data = "undo undo *";
        calculator.calculate(data);
        calculator.display();
        data = "5 *";
        calculator.calculate(data);
        calculator.display();
        data = "undo";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 5 4 3 2\n" +
                "stack: 20\n" +
                "stack: 100\n" +
                "stack: 20 5\n", os.toString());
    }

    @Test
    public void example5Test() {
        Calculator calculator = new RpnCalculator();
        String data = "7 12 2 /";
        calculator.calculate(data);
        calculator.display();
        data = "*";
        calculator.calculate(data);
        calculator.display();
        data = "4 /";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 7 6\n" +
                "stack: 42\n" +
                "stack: 10.5\n", os.toString());
    }

    @Test
    public void example6Test() {
        Calculator calculator = new RpnCalculator();
        String data = "1 2 3 4 5";
        calculator.calculate(data);
        calculator.display();
        data = "*";
        calculator.calculate(data);
        calculator.display();
        data = "clear 3 4 -";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 1 2 3 4 5\n" +
                "stack: 1 2 3 20\n" +
                "stack: -1\n", os.toString());
    }

    @Test
    public void example7Test() {
        Calculator calculator = new RpnCalculator();
        String data = "1 2 3 4 5";
        calculator.calculate(data);
        calculator.display();
        data = "* * * *";
        calculator.calculate(data);
        calculator.display();
        assertEquals("stack: 1 2 3 4 5\n" +
                "stack: 120\n", os.toString());
    }

    @Test
    public void example8Test() {
        try {
            Calculator calculator = new RpnCalculator();
            String data = "1 2 3 * 5 + * * 6 5";
            calculator.calculate(data);
            calculator.display();
        } catch (CalculatorException e) {
            assertTrue(e.getMessage().contains("insufficient parameters"));
        }
    }
}
