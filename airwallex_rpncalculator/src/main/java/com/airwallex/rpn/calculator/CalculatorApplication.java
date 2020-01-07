package com.airwallex.rpn.calculator;

import com.airwallex.rpn.calculator.factory.CalculatorFactory;
import com.airwallex.rpn.calculator.calculate.Calculator;
import com.airwallex.rpn.calculator.exception.CalculatorException;
import java.util.Scanner;

/**
 * @author : yangzanjie
 * @description: CalculatorApplication main method which accepts input from command line.
 * Commands are passed to RpnCalculatorController for calculating.
 */
public class CalculatorApplication {
    public static void main(String[] args) {
        Calculator calculator = CalculatorFactory.getCalculator();

        String input;
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                calculator.calculate(input);
            } catch (CalculatorException ex) {
                System.out.println(ex.getMessage());
            } finally {
                calculator.display();
            }
        }
    }
}
