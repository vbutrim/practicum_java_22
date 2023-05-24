package com.vbutrim.filmorate.api;

import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class NotValidExceptionResolver {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(NotValidExceptionResolver.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST) // 404
    public String methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        logger.error(ex);

        return "Not valid field: " + ex.getMessage();
    }

    @ExceptionHandler(value = {Exception.class})
    public String anyException(Exception ex, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {

        } //
        logger.error(ex);

        return "Not valid field: " + ex.getMessage();
    }
}