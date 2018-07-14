package ru.touchit.api.catalog.exception;

import ru.touchit.api.catalog.model.Country;

/**
 * Exception для обработки ситуации отсутствия страны {@link Country}
 * @author Artyom Karkavin
 */
public class NoSuchCountryException extends Exception {
    /**
     * {@inheritDoc}
     */
    public NoSuchCountryException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoSuchCountryException(String message, Throwable cause) {
        super(message, cause);
    }
}