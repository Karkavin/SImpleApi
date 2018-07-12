package ru.touchit.user.service;

import ru.touchit.catalog.exception.NoSuchCountryException;
import ru.touchit.catalog.exception.NoSuchDocException;
import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.user.exception.IncorrectDateException;
import ru.touchit.user.exception.NoSuchUserException;
import ru.touchit.user.view.BaseUserView;
import ru.touchit.user.view.FilterResultUserView;
import ru.touchit.user.view.FilterUserView;
import ru.touchit.user.view.FullUserView;

import java.util.List;

public interface UserService {
    FullUserView getById(Long id) throws NoSuchUserException;

    void add(BaseUserView userView) throws NoSuchOrganisationException, NoSuchOfficeException,
            IncorrectDateException, NoSuchDocException, NoSuchCountryException;

    void update(FullUserView userView) throws NoSuchUserException, NoSuchOrganisationException, NoSuchOfficeException,
            IncorrectDateException, NoSuchDocException, NoSuchCountryException;

    List<FilterResultUserView> filter(FilterUserView userView) throws NoSuchOfficeException, NoSuchDocException, NoSuchCountryException;
}