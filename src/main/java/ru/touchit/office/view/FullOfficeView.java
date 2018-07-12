package ru.touchit.office.view;

import ru.touchit.office.model.Office;

import javax.validation.constraints.NotNull;

public class FullOfficeView extends BaseOfficeView {
    @NotNull
    private long id;

    public FullOfficeView() {

    }

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