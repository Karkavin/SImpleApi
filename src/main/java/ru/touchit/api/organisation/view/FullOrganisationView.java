package ru.touchit.api.organisation.view;

import ru.touchit.api.organisation.model.Organisation;

import javax.validation.constraints.NotNull;

/**
 * View для представления подробной информации об организации {@link Organisation}
 * @author Artyom Karkavin
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