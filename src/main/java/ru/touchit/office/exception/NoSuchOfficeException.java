package ru.touchit.office.exception;

/**
 * Exception для обработки ситуации отсутствия офиса
 * @autor Artyom Karkavin
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