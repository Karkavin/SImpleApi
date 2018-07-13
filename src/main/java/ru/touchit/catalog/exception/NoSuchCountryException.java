package ru.touchit.catalog.exception;

/**
 * Exception для обработки ситуации отсутствия страны
 * @autor Artyom Karkavin
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