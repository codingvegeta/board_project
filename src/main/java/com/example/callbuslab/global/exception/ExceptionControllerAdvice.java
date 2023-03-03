package com.example.callbuslab.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ExceptionResult exceptionHandler(Exception e) {
        ExceptionResult exceptionResult = new ExceptionResult(e.getMessage());
        return exceptionResult;
    }
}
