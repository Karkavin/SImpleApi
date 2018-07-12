package ru.touchit.office.service;

import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.office.view.FilterOfficeView;
import ru.touchit.office.view.FilterResultOfficeView;
import ru.touchit.office.view.FullOfficeView;
import ru.touchit.office.view.NewOfficeView;
import ru.touchit.organisation.exception.NoSuchOrganisationException;

import java.util.List;

public interface OfficeService {
    FullOfficeView getById(Long id) throws NoSuchOfficeException;

    void add(NewOfficeView officeView) throws NoSuchOrganisationException;

    void update(FullOfficeView officeView) throws NoSuchOfficeException;

    List<FilterResultOfficeView> filter(FilterOfficeView officeView) throws NoSuchOrganisationException;
}