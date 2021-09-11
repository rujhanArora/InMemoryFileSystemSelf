package com.machineCoding.InMemoryFileSystemSelf.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super("Validation Exception: " + message);
    }
}