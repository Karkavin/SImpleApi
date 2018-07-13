package ru.touchit.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.touchit.catalog.model.Country;
import ru.touchit.catalog.model.Doc;
import ru.touchit.office.model.Office;
import ru.touchit.user.model.User;

import java.util.List;

/**
 * Dao для работы с сотрудниками
 * @autor Artyom Karkavin
 */
public interface UserDao extends CrudRepository<User, Long> {
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
     */
    @Query("SELECT u FROM User u WHERE " +
            "   u.office = :office" +
            "   AND LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) " +
            "   AND LOWER(u.secondName) LIKE LOWER(CONCAT('%', :secondName, '%')) " +
            "   AND LOWER(u.middleName) LIKE LOWER(CONCAT('%', :middleName, '%')) " +
            "   AND LOWER(u.position) LIKE LOWER(CONCAT('%', :position, '%')) " +
            "   AND (:doc IS NULL OR u.doc = :doc) " +
            "   AND (:country IS NULL OR u.country = :country)")
    List<User> findByOfficeAndNameAndPositionAndDocAndCountry(@Param("office") Office office,
                                                              @Param("firstName") String firstName,
                                                              @Param("secondName") String secondName,
                                                              @Param("middleName") String middleName,
                                                              @Param("position") String position,
                                                              @Param("doc") Doc doc,
                                                              @Param("country") Country country);
}