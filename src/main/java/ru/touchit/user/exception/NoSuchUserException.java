package ru.touchit.user.exception;

/**
 * Exception для обработки ситуации отсутствия сотрудника
 * @autor Artyom Karkavin
 */
public class NoSuchUserException extends Exception {
    /**
     * {@inheritDoc}
     */
    public NoSuchUserException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public NoSuchUserException(String message, Throwable cause) {
        super(message, cause);
    }
}