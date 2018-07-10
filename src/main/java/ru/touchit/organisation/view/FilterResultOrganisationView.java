package ru.touchit.organisation.view;

public class FilterResultOrganisationView {
    private long id;
    private String name;
    private boolean isActive;

    public FilterResultOrganisationView() {

    }

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