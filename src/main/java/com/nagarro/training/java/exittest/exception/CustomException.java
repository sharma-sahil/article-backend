package com.nagarro.training.java.exittest.exception;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -478376748646484516L;

    String errorCode;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
