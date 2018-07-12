package ru.touchit.user.exception;

public class OfficeDoesNotInOrganisationException extends Exception {
    public OfficeDoesNotInOrganisationException(String message) {
        super(message);
    }

    public OfficeDoesNotInOrganisationException(String message, Throwable cause) {
        super(message, cause);
    }
}