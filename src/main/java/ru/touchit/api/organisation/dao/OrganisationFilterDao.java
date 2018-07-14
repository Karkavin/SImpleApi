package ru.touchit.api.organisation.dao;

import ru.touchit.api.organisation.model.Organisation;

import java.util.List;

/**
 * Dao с фильтрами для работы с организациями {@link Organisation}
 * @author Artyom Karkavin
 */
public interface OrganisationFilterDao {
    /**
     * Получение списка организаций с применением фильтров
     * @param name наименование
     * @param inn ИНН
     * @param isActive статус (действующая ли)
     * @return список организаций
     * @see Organisation
     */
    List<Organisation> filter(String name, String inn, Boolean isActive);
}