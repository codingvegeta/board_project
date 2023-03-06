package com.example.securityBoard.global.exception;

public class DuplicateClientException extends RuntimeException{
    public DuplicateClientException() {
        super();
    }

    public DuplicateClientException(String message) {
        super(message);
    }

    public DuplicateClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateClientException(Throwable cause) {
        super(cause);
    }

    protected DuplicateClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
