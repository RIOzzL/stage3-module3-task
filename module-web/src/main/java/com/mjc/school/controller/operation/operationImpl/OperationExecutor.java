package com.mjc.school.controller.operation.operationImpl;

import com.mjc.school.service.exception.InputErrorMessage;
import com.mjc.school.service.exception.InputValidatorException;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    protected Set<Long> validateNumberArrayInput(String param) {
        Set<Long> tagsId;
        try {
            tagsId = Stream.of(scanner.nextLine().split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
        } catch (RuntimeException exception) {
            throw new InputValidatorException(String.format(InputErrorMessage.VALIDATE_INT_VALUE.getMessage(), param));
        }
        return tagsId;
    }
}
