package com.example.demo.exceptions;

public class CustomException extends RuntimeException {
    private final String errorCode;

    public CustomException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode(){
        return errorCode;
    }
}
