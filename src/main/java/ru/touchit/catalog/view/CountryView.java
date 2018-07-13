package ru.touchit.catalog.view;

import ru.touchit.catalog.model.Country;

/**
 * View для представления страны
 * @autor Artyom Karkavin
 */
public class CountryView {
    /** Поле: код страны */
    private Short code;

    /** Поле: наименование */
    private String name;

    /**
     * Конструктор
     * @param country Entity сущности Страна
     */
    public CountryView(Country country) {
        this.code = country.getCode();
        this.name = country.getName();
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}