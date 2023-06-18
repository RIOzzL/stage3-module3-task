package com.mjc.school.service.exception;

public enum InputErrorMessage {
    VALIDATE_LONG_VALUE("000013", "%s should be number"),

    VALIDATE_INT_ARRAY_VALUE("000030", "%s must be numbers entered with spaces");

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
