package ru.touchit.office.exception;

public class NoSuchOfficeException extends Exception {
    public NoSuchOfficeException(String message) {
        super(message);
    }

    public NoSuchOfficeException(String message, Throwable cause) {
        super(message, cause);
    }
}