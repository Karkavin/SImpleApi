package ru.touchit.api.catalog.view;

import ru.touchit.api.catalog.model.Country;

/**
 * View для представления страны {@link Country}
 * @author Artyom Karkavin
 */
public class CountryView {
    /** Поле: код страны */
    private Short code;

    /** Поле: наименование */
    private String name;

    /**
     * Конструктор
     * @param country Entity сущности Страна
     * @see Country
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