package ru.touchit.organisation.view;

import ru.touchit.organisation.model.Organisation;

import javax.validation.constraints.NotNull;

/**
 * View для представления подробной информации об организации
 * @autor Artyom Karkavin
 */
public class FullOrganisationView extends BaseOrganisationView {
    /** Поле: идентификатор */
    @NotNull
    private long id;

    /**
     * Конструктор
     */
    public FullOrganisationView() {

    }

    /**
     * {@inheritDoc}
     */
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