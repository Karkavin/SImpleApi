package ru.touchit.catalog.view;

import ru.touchit.catalog.model.Doc;

/**
 * View для представления документа
 * @autor Artyom Karkavin
 */
public class DocView {
    /** Поле: код документа */
    private Short code;

    /** Поле: наименование */
    private String name;

    /**
     * Конструктор
     * @param doc Entity сущности Документ
     */
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