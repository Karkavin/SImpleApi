package ru.touchit.organisation.service;

import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.model.Organisation;
import ru.touchit.organisation.view.BaseOrganisationView;
import ru.touchit.organisation.view.FilterOrganisationView;
import ru.touchit.organisation.view.FilterResultOrganisationView;
import ru.touchit.organisation.view.FullOrganisationView;

import java.util.List;

public interface OrganisationService {
    Organisation getById(Long id) throws NoSuchOrganisationException;

    void add(BaseOrganisationView organisationView);

    void update(FullOrganisationView organisationView) throws NoSuchOrganisationException;

    List<FilterResultOrganisationView> filter(FilterOrganisationView organisationView);
}