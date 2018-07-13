package ru.touchit.office.exception;

/**
 * Exception для обработки ситуации отсутствия факта вхождения офиса в состав организации
 * @autor Artyom Karkavin
 */
public class OfficeDoesNotInOrganisationException extends Exception {
    /**
     * {@inheritDoc}
     */
    public OfficeDoesNotInOrganisationException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public OfficeDoesNotInOrganisationException(String message, Throwable cause) {
        super(message, cause);
    }
}