package ru.touchit.organisation.view;

import ru.touchit.organisation.model.Organisation;

import javax.validation.constraints.NotNull;

public class FullOrganisationView extends BaseOrganisationView {
    @NotNull
    private long id;

    public FullOrganisationView() {

    }

    public FullOrganisationView(Organisation organisation) {
        super(organisation);

        this.id = organisation.getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}