package ru.touchit.organisation.exception;

/**
 * Exception для обработки ситуации отсутствия организации
 * @autor Artyom Karkavin
 */
public class NoSuchOrganisationException extends Exception {
    /**
     * {@inheritDoc}
     */
    public NoSuchOrganisationException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoSuchOrganisationException(String message, Throwable cause) {
        super(message, cause);
    }
}