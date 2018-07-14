package ru.touchit.api.user.dao;

import ru.touchit.api.catalog.model.Country;
import ru.touchit.api.catalog.model.Doc;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.user.model.User;

import java.util.List;

/**
 * Dao с фильтрами для работы с сотрудниками {@link User}
 * @author Artyom Karkavin
 */
public interface UserFilterDao {
    /**
     * Получение списка сотрудников офиса в организации с применением фильтров
     * @param office сущность Офис
     * @param firstName имя
     * @param secondName фамилия
     * @param middleName отчество
     * @param position должность
     * @param doc сущность Документ
     * @param country сущность Страна
     * @return список сотрудников офиса в организации
     * @see User
     */
    List<User> filter(Office office, String firstName, String secondName,
                      String middleName, String position, Doc doc, Country country);
}