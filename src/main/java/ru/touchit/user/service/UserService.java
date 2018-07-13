package ru.touchit.user.service;

import ru.touchit.catalog.exception.NoSuchCountryException;
import ru.touchit.catalog.exception.NoSuchDocException;
import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.user.exception.IncorrectDateException;
import ru.touchit.user.exception.NoSuchUserException;
import ru.touchit.office.exception.OfficeDoesNotInOrganisationException;
import ru.touchit.user.view.BaseUserView;
import ru.touchit.user.view.FilterResultUserView;
import ru.touchit.user.view.FilterUserView;
import ru.touchit.user.view.FullUserView;

import java.util.List;

/**
 * Сервис для работы с сотрудниками
 * @autor Artyom Karkavin
 */
public interface UserService {
    /**
     * Получение подробной информации о сотруднике по id, объект FullUserView
     * @param id идентификатор сотрудника
     * @throws NoSuchUserException сотрудник не найден
     * @return подробная информация о сотруднике
     */
    FullUserView getById(Long id) throws NoSuchUserException;

    /**
     * Добавление нового сотрудника в офис организации
     * @param userView добавляемая информация
     * @throws NoSuchOrganisationException организация не найдена
     * @throws NoSuchOfficeException офис не найден
     * @throws IncorrectDateException неверно указанный формат даты
     * @throws NoSuchDocException документ не найден
     * @throws NoSuchCountryException страна не найдена
     * @throws OfficeDoesNotInOrganisationException офис не входит в организацию
     */
    void add(BaseUserView userView) throws NoSuchOrganisationException, NoSuchOfficeException,
            IncorrectDateException, NoSuchDocException, NoSuchCountryException, OfficeDoesNotInOrganisationException;

    /**
     * Обновление информации о сотруднике
     * @param userView обновляемая информация
     * @throws NoSuchUserException сотрудник не найден
     * @throws NoSuchOrganisationException организация не найдена
     * @throws NoSuchOfficeException офис не найден
     * @throws IncorrectDateException неверно указанный формат даты
     * @throws NoSuchDocException документ не найден
     * @throws NoSuchCountryException страна не найдена
     * @throws OfficeDoesNotInOrganisationException офис не входит в организацию
     */
    void update(FullUserView userView) throws NoSuchUserException, NoSuchOrganisationException, NoSuchOfficeException,
            IncorrectDateException, NoSuchDocException, NoSuchCountryException, OfficeDoesNotInOrganisationException;

    /**
     * Получение списка сотрудников в офисе организации с применением фильтров, список объектов FilterResultUserView
     * @param userView применяемые фильтры
     * @throws NoSuchOfficeException офис не найден
     * @throws NoSuchDocException документ не найден
     * @throws NoSuchCountryException страна не найдена
     * @return список сотрудников
     */
    List<FilterResultUserView> filter(FilterUserView userView) throws NoSuchOfficeException,
            NoSuchDocException, NoSuchCountryException;
}