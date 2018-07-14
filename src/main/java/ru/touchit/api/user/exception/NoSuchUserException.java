package ru.touchit.api.user.exception;

import ru.touchit.api.user.model.User;

/**
 * Exception для обработки ситуации отсутствия сотрудника {@link User}
 * @author Artyom Karkavin
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