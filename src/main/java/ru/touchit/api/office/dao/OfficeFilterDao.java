package ru.touchit.api.office.dao;

import ru.touchit.api.office.model.Office;
import ru.touchit.api.organisation.model.Organisation;

import java.util.List;

/**
 * Dao с фильтрами для работы с офисами {@link Office}
 * @author Artyom Karkavin
 */
public interface OfficeFilterDao {
    /**
     * Получение списка офисов организации с применением фильтров
     * @param organisation сущность Организация
     * @param name наименование
     * @param phone телефон
     * @param isActive статус офиса (активен ли)
     * @return список офисов организации
     * @see Office
     */
    List<Office> filter(Organisation organisation, String name, String phone, Boolean isActive);
}