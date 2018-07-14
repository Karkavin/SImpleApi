package ru.touchit.api.office.service;

import ru.touchit.api.office.exception.NoSuchOfficeException;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.office.view.BaseOfficeView;
import ru.touchit.api.office.view.FilterOfficeView;
import ru.touchit.api.office.view.FilterResultOfficeView;
import ru.touchit.api.office.view.FullOfficeView;
import ru.touchit.api.organisation.exception.NoSuchOrganisationException;

import java.util.List;

/**
 * Сервис для работы с офисами {@link Office}
 * @author Artyom Karkavin
 */
public interface OfficeService {
    /**
     * Получение подробной информации об офисе по id, объект FullOfficeView
     * @param id идентификатор офиса
     * @throws NoSuchOfficeException офис не найден
     * @return подробная информация об офисе
     * @see FullOfficeView
     */
    FullOfficeView getById(Long id) throws NoSuchOfficeException;

    /**
     * Добавление нового офиса для организации
     * @param officeView добавляемая информация
     * @throws NoSuchOrganisationException организация не найдена
     * @see BaseOfficeView
     */
    void add(BaseOfficeView officeView) throws NoSuchOrganisationException;

    /**
     * Обновление информации офиса
     * @param officeView обновляемая информация
     * @throws NoSuchOfficeException офис не найден
     * @throws NoSuchOrganisationException организация не найдена
     * @see FullOfficeView
     */
    void update(FullOfficeView officeView) throws NoSuchOfficeException, NoSuchOrganisationException;

    /**
     * Получение списка офисов организации с применением фильтров, список объектов FilterResultOfficeView
     * @param officeView применяемые фильтры
     * @throws NoSuchOrganisationException организация не найдена
     * @return список офисов
     * @see FilterResultOfficeView
     * @see FilterOfficeView
     */
    List<FilterResultOfficeView> filter(FilterOfficeView officeView) throws NoSuchOrganisationException;
}