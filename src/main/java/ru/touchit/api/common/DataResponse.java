package ru.touchit.api.common;

/**
 * Response успешно обработанного запроса
 * @author Artyom Karkavin
 */
public class DataResponse<T> {
    /** Поле: содержимое ответа */
    private T data;

    /**
     * Конструктор
     */
    public DataResponse() {

    }

    /**
     * Конструктор
     * @param data содержимое ответа
     */
    public DataResponse(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}