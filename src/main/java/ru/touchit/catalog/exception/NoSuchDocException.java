package ru.touchit.catalog.exception;

public class NoSuchDocException extends Exception {
    public NoSuchDocException(String message) {
        super(message);
    }

    public NoSuchDocException(String message, Throwable cause) {
        super(message, cause);
    }
}