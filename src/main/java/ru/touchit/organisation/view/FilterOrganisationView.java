package ru.touchit.organisation.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View для представления запрашиваемых фильтров
 * @autor Artyom Karkavin
 */
public class FilterOrganisationView {
    /** Поле: наименование */
    @NotNull
    @Size(max = 50)
    private String name;

    /** Поле: ИНН */
    @Size(min = 10, max = 10)
    private String inn;

    /** Поле: статус (действующая ли) */
    private Boolean isActive;

    /**
     * Конструктор
     */
    public FilterOrganisationView() {

    }

    /**
     * Конструктор
     * @param name наименование
     * @param inn ИНН
     * @param isActive статус (действующая ли)
     */
    public FilterOrganisationView(String name, String inn, boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}