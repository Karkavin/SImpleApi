package ru.touchit.office.service;

import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.office.view.BaseOfficeView;
import ru.touchit.office.view.FilterOfficeView;
import ru.touchit.office.view.FilterResultOfficeView;
import ru.touchit.office.view.FullOfficeView;
import ru.touchit.organisation.exception.NoSuchOrganisationException;

import java.util.List;

/**
 * Сервис для работы с офисами
 * @autor Artyom Karkavin
 */
public interface OfficeService {
    /**
     * Получение подробной информации об офисе по id, объект FullOfficeView
     * @param id идентификатор офиса
     * @throws NoSuchOfficeException офис не найден
     * @return подробная информация об офисе
     */
    FullOfficeView getById(Long id) throws NoSuchOfficeException;

    /**
     * Добавление нового офиса для организации
     * @param officeView добавляемая информация
     * @throws NoSuchOrganisationException организация не найдена
     */
    void add(BaseOfficeView officeView) throws NoSuchOrganisationException;

    /**
     * Обновление информации офиса
     * @param officeView обновляемая информация
     * @throws NoSuchOfficeException офис не найден
     * @throws NoSuchOrganisationException организация не найдена
     */
    void update(FullOfficeView officeView) throws NoSuchOfficeException, NoSuchOrganisationException;

    /**
     * Получение списка офисов организации с применением фильтров, список объектов FilterResultOfficeView
     * @param officeView применяемые фильтры
     * @throws NoSuchOrganisationException организация не найдена
     * @return список офисов
     */
    List<FilterResultOfficeView> filter(FilterOfficeView officeView) throws NoSuchOrganisationException;
}