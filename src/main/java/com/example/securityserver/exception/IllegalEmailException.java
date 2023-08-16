package com.example.securityserver.exception;

public class IllegalEmailException extends RuntimeException {
    public IllegalEmailException() {
    }

    public IllegalEmailException(String message) {
        super(message);
    }
}
