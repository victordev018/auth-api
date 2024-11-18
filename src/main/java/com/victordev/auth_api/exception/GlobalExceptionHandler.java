package com.victordev.auth_api.exception;

import com.victordev.auth_api.controller.exception.CustomAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomAuthenticationException.class)
    public ResponseEntity<StandardError> customAuthenticationException(CustomAuthenticationException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;    // 401
        StandardError response = new StandardError(status.value(), "Authentication Error.", exception.getMessage());
        return ResponseEntity.status(status.value()).body(response);
    }

//    @ExceptionHandler(CustomJwtVerificationException.class)
//    public ResponseEntity<StandardError> customJwtVerificationException(CustomJwtVerificationException exception) {
//        HttpStatus status = HttpStatus.UNAUTHORIZED;    // 401
//        StandardError response = new StandardError(status.value(), "Jwt Verification Error.", exception.getMessage());
//        return ResponseEntity.status(status.value()).body(response);
//    }
}
