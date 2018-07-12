package ru.touchit.office.view;

public class FilterResultOfficeView {
    private long id;

    private String name;

    private Boolean isActive;

    public FilterResultOfficeView() {

    }

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