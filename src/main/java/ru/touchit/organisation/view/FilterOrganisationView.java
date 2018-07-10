package ru.touchit.organisation.view;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilterOrganisationView {
    @NotNull
    @Size(max = 50)
    private String name;

    @Size(min = 10, max = 10)
    private String inn;

    private Boolean isActive;

    public FilterOrganisationView() {

    }

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