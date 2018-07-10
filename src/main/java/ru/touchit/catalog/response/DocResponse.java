package ru.touchit.catalog.response;

public class DocResponse {
    private Short code;
    private String name;

    public DocResponse() {

    }

    public DocResponse(Short code, String name) {
        this.code = code;
        this.name = name;
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