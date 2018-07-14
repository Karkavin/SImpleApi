package ru.touchit.api.office.exception;

import ru.touchit.api.office.model.Office;
import ru.touchit.api.organisation.model.Organisation;

/**
 * Exception для обработки ситуации отсутствия факта вхождения офиса {@link Office} в состав организации {@link Organisation}
 * @author Artyom Karkavin
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