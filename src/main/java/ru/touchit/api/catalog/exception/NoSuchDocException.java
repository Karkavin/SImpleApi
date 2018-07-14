package ru.touchit.api.catalog.exception;

import ru.touchit.api.catalog.model.Doc;

/**
 * Exception для обработки ситуации отсутствия документа {@link Doc}
 * @author Artyom Karkavin
 */
public class NoSuchDocException extends Exception {
    /**
     * {@inheritDoc}
     */
    public NoSuchDocException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoSuchDocException(String message, Throwable cause) {
        super(message, cause);
    }
}