package ru.touchit.api.office.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.organisation.model.Organisation;

import java.util.List;

/**
 * Dao для работы с офисами {@link Office}
 * @author Artyom Karkavin
 */
public interface OfficeDao extends CrudRepository<Office, Long> {
    /**
     * Получение списка офисов организации с применением фильтров
     * @param organisation сущность Организация
     * @param name наименование
     * @param phone телефон
     * @return список офисов организации
     * @see Office
     */
    @Query("SELECT o FROM Office o WHERE " +
            "   o.organisation = :organisation " +
            "   AND LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.phone LIKE CONCAT('%', :phone, '%')")
    List<Office> findByOrgIdAndNameAndPhone(@Param("organisation") Organisation organisation,
                                            @Param("name") String name,
                                            @Param("phone") String phone);

    /**
     * Получение списка офисов организации с применением фильтров
     * @param organisation сущность Организация
     * @param name наименование
     * @param phone телефон
     * @param active статус офиса (активен ли)
     * @return список офисов организации
     * @see Office
     */
    @Query("SELECT o FROM Office o WHERE " +
            "   o.organisation = :organisation " +
            "   AND LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.phone LIKE CONCAT('%', :phone, '%')" +
            "   AND o.isActive = :active")
    List<Office> findByOrgIdAndNameAndPhoneAndIsActive(@Param("organisation") Organisation organisation,
                                                       @Param("name") String name,
                                                       @Param("phone") String phone,
                                                       @Param("active") boolean active);
}