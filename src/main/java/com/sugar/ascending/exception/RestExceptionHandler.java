package com.sugar.ascending.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getFormattedMessage());
    }

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity handleUserNotFoundException(CustomerNotFoundException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getFormattedMessage());
    }
}
