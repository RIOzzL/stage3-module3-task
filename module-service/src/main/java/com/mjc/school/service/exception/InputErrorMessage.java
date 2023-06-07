package com.mjc.school.service.exception;

public enum InputErrorMessage {
    VALIDATE_INT_VALUE("000013","%s should be number");

    private final String errorMessage;

    private InputErrorMessage(String errorCode, String message) {
        this.errorMessage = "ERROR_CODE: " + errorCode + " ERROR_MESSAGE: " + message;
    }

    public String getMessage() {
        return this.errorMessage;
    }

    public String getErrorCode() {
        return this.errorMessage;
    }
}
