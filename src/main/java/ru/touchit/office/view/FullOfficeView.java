package ru.touchit.office.view;

import ru.touchit.office.model.Office;

import javax.validation.constraints.NotNull;

/**
 * View для представления подробной информации об офисе
 * @autor Artyom Karkavin
 */
public class FullOfficeView extends BaseOfficeView {
    /** Поле: идентификатор */
    @NotNull
    private long id;

    /**
     * Конструктор
     */
    public FullOfficeView() {

    }

    /**
     * {@inheritDoc}
     */
    public FullOfficeView(Office office) {
        super(office);

        this.id = office.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}