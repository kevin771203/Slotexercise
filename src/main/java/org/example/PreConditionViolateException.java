package org.example;

public class PreConditionViolateException extends RuntimeException {
    public PreConditionViolateException(String message) {
        super(message);
    }
}
