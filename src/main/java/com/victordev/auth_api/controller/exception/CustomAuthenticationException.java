package com.victordev.auth_api.controller.exception;

public class CustomAuthenticationException extends RuntimeException {

    private static final Long serialVersionUUID = 1L;

    public CustomAuthenticationException(String message) {
        super(message);
    }
}
