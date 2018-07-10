package ru.touchit.catalog.view;

import ru.touchit.catalog.model.Doc;

public class DocView {
    private Short code;
    private String name;

    public DocView(Doc doc) {
        this.code = doc.getCode();
        this.name = doc.getName();
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