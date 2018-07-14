package ru.touchit.api.catalog.view;

import ru.touchit.api.catalog.model.Doc;

/**
 * View для представления документа {@link Doc}
 * @author Artyom Karkavin
 */
public class DocView {
    /** Поле: код документа */
    private Short code;

    /** Поле: наименование */
    private String name;

    /**
     * Конструктор
     * @param doc Entity сущности Документ
     * @see Doc
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