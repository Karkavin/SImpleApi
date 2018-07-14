package ru.touchit.api.office.view;

import ru.touchit.api.office.model.Office;

/**
 * View для представления результатов работы фильтров {@link Office}
 * @author Artyom Karkavin
 */
public class FilterResultOfficeView {
    /** Поле: идентификатор */
    private long id;

    /** Поле: наименование */
    private String name;

    /** Поле: статус офиса (активен ли) */
    private Boolean isActive;

    /**
     * Конструктор
     */
    public FilterResultOfficeView() {

    }

    /**
     * Конструктор
     * @param id идентификатор
     * @param name наименование
     * @param isActive статус офиса (активен ли)
     */
    public FilterResultOfficeView(long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}