package ru.touchit.catalog.exception;

public class NoSuchCountryException extends Exception {
    public NoSuchCountryException(String message) {
        super(message);
    }

    public NoSuchCountryException(String message, Throwable cause) {
        super(message, cause);
    }
}