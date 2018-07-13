package ru.touchit.catalog.exception;

/**
 * Exception для обработки ситуации отсутствия документа
 * @autor Artyom Karkavin
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