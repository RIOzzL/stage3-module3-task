package com.mjc.school.service.exception;

public class InputValidatorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InputValidatorException(String message) {
        super(message);
    }
}
