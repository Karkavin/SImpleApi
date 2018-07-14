package ru.touchit.api.organisation.exception;

import ru.touchit.api.organisation.model.Organisation;

/**
 * Exception для обработки ситуации отсутствия организации {@link Organisation}
 * @author Artyom Karkavin
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