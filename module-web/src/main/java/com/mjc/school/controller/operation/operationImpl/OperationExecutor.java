package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.service.exception.InputErrorMessage;
import com.mjc.school.service.exception.InputValidatorException;

import java.util.Scanner;

public abstract class OperationExecutor {
    protected final Scanner scanner = new Scanner(System.in);

    protected Long validateNumberInput(String param) {
        long nextLong;
        try {
            nextLong = scanner.nextLong();
        } catch (RuntimeException exception) {
            throw new InputValidatorException(String.format(InputErrorMessage.VALIDATE_INT_VALUE.getMessage(), param));
        }
        return nextLong;
    }
}
