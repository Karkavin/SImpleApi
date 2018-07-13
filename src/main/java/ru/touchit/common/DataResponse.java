package ru.touchit.common;

/**
 * Response успешно обработанного запроса
 * @autor Artyom Karkavin
 */
public class DataResponse<T> {
    /** Поле: содержимое ответа */
    private T data;

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