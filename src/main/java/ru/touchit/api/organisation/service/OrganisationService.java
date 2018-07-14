package ru.touchit.api.organisation.service;

import ru.touchit.api.organisation.exception.NoSuchOrganisationException;
import ru.touchit.api.organisation.model.Organisation;
import ru.touchit.api.organisation.view.BaseOrganisationView;
import ru.touchit.api.organisation.view.FilterOrganisationView;
import ru.touchit.api.organisation.view.FilterResultOrganisationView;
import ru.touchit.api.organisation.view.FullOrganisationView;

import java.util.List;

/**
 * Сервис для работы с организациями {@link Organisation}
 * @author Artyom Karkavin
 */
public interface OrganisationService {
    /**
     * Получение подробной информации об организации по id, объект FullOrganisationView
     * @param id идентификатор организации
     * @throws NoSuchOrganisationException организация не найдена
     * @return подробная информация об организации
     * @see FullOrganisationView
     */
    FullOrganisationView getById(Long id) throws NoSuchOrganisationException;

    /**
     * Добавление новой организации
     * @param organisationView добавляемая информация
     * @see BaseOrganisationView
     */
    void add(BaseOrganisationView organisationView);

    /**
     * Обновление информации об организации
     * @param organisationView обновляемая информация
     * @throws NoSuchOrganisationException организация не найдена
     * @see FullOrganisationView
     */
    void update(FullOrganisationView organisationView) throws NoSuchOrganisationException;

    /**
     * Получение списка организаций с применением фильтров, список объектов FilterResultOrganisationView
     * @param organisationView применяемые фильтры
     * @return список организаций
     * @see FilterResultOrganisationView
     * @see FilterOrganisationView
     */
    List<FilterResultOrganisationView> filter(FilterOrganisationView organisationView);
}