package ru.touchit.catalog.view;

import ru.touchit.catalog.model.Country;

public class CountryView {
    private Short code;
    private String name;

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