package com.nagarro.training.java.exittest.dto;

import com.nagarro.training.java.exittest.exception.CustomException;

import java.util.Map;

public class ErrorResponse {
    private String message;
    private String errorCode;

    Map<String, String> errors;

    public ErrorResponse() {
    }

    public ErrorResponse(CustomException ex) {
        this.message = ex.getMessage();
        this.errorCode = ex.getErrorCode();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
