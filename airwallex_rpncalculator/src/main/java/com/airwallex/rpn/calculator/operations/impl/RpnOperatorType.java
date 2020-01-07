package com.airwallex.rpn.calculator.operations.impl;

import com.airwallex.rpn.calculator.exception.CalculatorException;
import com.airwallex.rpn.calculator.operations.Operator;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : yangzanjie
 * @description: Enum specific operator.
 */
public enum RpnOperatorType {

    ADD("+", ((stack, operationList, position) -> {
        RpnOperator.checkStack(stack, position, "+");
        stack.push(stack.pop().add(stack.pop()));
        RpnOperator.updateTracker(stack, operationList);
    })),

    SUBTRACT("-", ((stack, operationList, position) -> {
        RpnOperator.checkStack(stack, position, "-");
        BigDecimal first = stack.pop();
        stack.push(stack.pop().subtract(first));
        RpnOperator.updateTracker(stack, operationList);
    })),

    MULTIPLY("*", ((stack, operationList, position) -> {
        RpnOperator.checkStack(stack, position, "*");
        stack.push(stack.pop().multiply(stack.pop()));
        RpnOperator.updateTracker(stack, operationList);
    })),

    DIVIDE("/", ((stack, operationList, position) -> {
        RpnOperator.checkStack(stack, position, "/");
        BigDecimal first = stack.pop();
        if (first.compareTo(BigDecimal.ZERO) == 0)
            throw new CalculatorException("/", "Divide by Zero Error");
        stack.push(new BigDecimal(stack.pop().doubleValue() / first.doubleValue()));
        RpnOperator.updateTracker(stack, operationList);
    })),

    SQRT("SQRT", ((stack, operationList, position) -> {
        RpnOperator.checkStack(stack, position, "SQRT");
        stack.push(BigDecimal.valueOf(Math.sqrt(stack.pop().doubleValue())));
        RpnOperator.updateTracker(stack, operationList);
    })),

    CLEAR("CLEAR", ((stack, operationList, position) -> {
        stack.clear();
        RpnOperator.updateTracker(stack, operationList);
    })),

    UNDO("UNDO", ((stack, operationList, position) -> {
        if (operationList.isEmpty())
            throw new CalculatorException("Can't Undo");
        stack.clear();
        operationList.remove(operationList.size() - 1);
        if (!operationList.isEmpty())
            operationList.get(operationList.size() - 1).getTracker().stream().forEach(element -> stack.push(element));
    }));


    private final String operatorName;

    private final Operator operator;

    RpnOperatorType(String operatorName, Operator operator) {
        this.operatorName = operatorName;
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public Operator getOperator() {
        return operator;
    }

    public static final Map<String, RpnOperatorType> SUPPORTED_OPERATIONS  =
            Collections.unmodifiableMap(Stream.of(
                    new AbstractMap.SimpleEntry<>(ADD.getOperatorName(), ADD),
                    new AbstractMap.SimpleEntry<>(SUBTRACT.getOperatorName(), SUBTRACT),
                    new AbstractMap.SimpleEntry<>(MULTIPLY.getOperatorName(), MULTIPLY),
                    new AbstractMap.SimpleEntry<>(DIVIDE.getOperatorName(), DIVIDE),
                    new AbstractMap.SimpleEntry<>(SQRT.getOperatorName(), SQRT),
                    new AbstractMap.SimpleEntry<>(CLEAR.getOperatorName(), CLEAR),
                    new AbstractMap.SimpleEntry<>(UNDO.getOperatorName(), UNDO))
                    .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue())));
}
