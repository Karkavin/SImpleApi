package ru.touchit.api.common;

/**
 * Response запроса с ошибкой (неверные данные, неверный формат данных и т.д.)
 * @author Artyom Karkavin
 */
public class ErrorResponse {
    /** Поле: пояснение ошибки */
    private String error;

    /**
     * Конструктор
     * @param error пояснение ошибки
     */
    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}