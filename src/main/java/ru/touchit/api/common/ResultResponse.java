package ru.touchit.api.common;

/**
 * Response успешно обработанного запроса с коротким ответом
 * @author Artyom Karkavin
 */
public class ResultResponse {
    /** Поле: результат работы запроса */
    private String result;

    /**
     * Конструктор
     * @param result результат работы запроса
     */
    public ResultResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}