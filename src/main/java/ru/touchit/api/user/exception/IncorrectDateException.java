package ru.touchit.api.user.exception;

/**
 * Exception для обработки ситуации указания неверного формата даты
 * @author Artyom Karkavin
 */
public class IncorrectDateException extends Exception {
    /**
     * {@inheritDoc}
     */
    public IncorrectDateException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public IncorrectDateException(String message, Throwable cause) {
        super(message, cause);
    }
}