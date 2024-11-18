package com.victordev.auth_api.infra.exception;

public class TokenNotProviderException extends RuntimeException {

    private static final Long serialVersionUUID = 1L;

    public TokenNotProviderException(String message) {
        super(message);
    }
}
