package com.victordev.auth_api.exception;

public record StandardError(int status, String error, String message) {
}
