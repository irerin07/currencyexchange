package com.example.currencyexchange.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

@ControllerAdvice
@Slf4j
public class ExceptionHandling {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity exception(NullPointerException exception) {
        log.error("NullPointerException in the class: " + exception.getClass().getName() + "with : " + exception.getMessage() + ". Cause: " + exception.getCause());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity exception(IllegalArgumentException exception) {
        log.error("IllegalArgumentException in the class: " + exception.getClass().getName() + "with : " + exception.getMessage() + ". Cause: " + exception.getCause());
        return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity exception(RestClientException exception) {
        log.error("RestClientException in the class: " + exception.getClass().getName() + "with : " + exception.getMessage() + ". Cause: " + exception.getCause());
        return new ResponseEntity(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
