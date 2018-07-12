package ru.touchit.user.exception;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(String message) {
        super(message);
    }

    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}