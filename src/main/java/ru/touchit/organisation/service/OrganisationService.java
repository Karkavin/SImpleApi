package ru.touchit.organisation.service;

import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.view.BaseOrganisationView;
import ru.touchit.organisation.view.FilterOrganisationView;
import ru.touchit.organisation.view.FilterResultOrganisationView;
import ru.touchit.organisation.view.FullOrganisationView;

import java.util.List;

/**
 * Сервис для работы с организациями
 * @autor Artyom Karkavin
 */
public interface OrganisationService {
    /**
     * Получение подробной информации об организации по id, объект FullOrganisationView
     * @param id идентификатор организации
     * @throws NoSuchOrganisationException организация не найдена
     * @return подробная информация об организации
     */
    FullOrganisationView getById(Long id) throws NoSuchOrganisationException;

    /**
     * Добавление новой организации
     * @param organisationView добавляемая информация
     */
    void add(BaseOrganisationView organisationView);

    /**
     * Обновление информации об организации
     * @param organisationView обновляемая информация
     * @throws NoSuchOrganisationException организация не найдена
     */
    void update(FullOrganisationView organisationView) throws NoSuchOrganisationException;

    /**
     * Получение списка организаций с применением фильтров, список объектов FilterResultOrganisationView
     * @param organisationView применяемые фильтры
     * @return список организаций
     */
    List<FilterResultOrganisationView> filter(FilterOrganisationView organisationView);
}