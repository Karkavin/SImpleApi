package ru.touchit.organisation.exception;

public class NoSuchOrganisationException extends Exception {
    public NoSuchOrganisationException(String message) {
        super(message);
    }

    public NoSuchOrganisationException(String message, Throwable cause) {
        super(message, cause);
    }
}