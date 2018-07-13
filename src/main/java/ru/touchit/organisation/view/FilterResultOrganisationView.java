package ru.touchit.organisation.view;

/**
 * View для представления результатов работы фильтров
 * @autor Artyom Karkavin
 */
public class FilterResultOrganisationView {
    /** Поле: идентификатор */
    private long id;

    /** Поле: наименование */
    private String name;

    /** Поле: статус (действующая ли) */
    private boolean isActive;

    /**
     * Конструктор
     */
    public FilterResultOrganisationView() {

    }

    /**
     * Конструктор
     * @param id идентификатор
     * @param name наименование
     * @param isActive статус (действующая ли)
     */
    public FilterResultOrganisationView(long id, String name, boolean isActive) {
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}