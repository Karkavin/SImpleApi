package ru.touchit.api.office.exception;

import ru.touchit.api.office.model.Office;

/**
 * Exception для обработки ситуации отсутствия офиса {@link Office}
 * @author Artyom Karkavin
 */
public class NoSuchOfficeException extends Exception {
    /**
     * {@inheritDoc}
     */
    public NoSuchOfficeException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoSuchOfficeException(String message, Throwable cause) {
        super(message, cause);
    }
}