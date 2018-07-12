package ru.touchit.user.exception;

public class IncorrectDateException extends Exception {
    public IncorrectDateException(String message) {
        super(message);
    }

    public IncorrectDateException(String message, Throwable cause) {
        super(message, cause);
    }
}