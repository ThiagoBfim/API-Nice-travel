package com.nicetravel.nicetravel.exceptions;

public class IntegrationException extends RuntimeException {

    public IntegrationException(String message) {
        super("Houve um problema de integração: ".concat(message));
    }
}
