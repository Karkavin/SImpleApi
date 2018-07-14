package ru.touchit.api.organisation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.touchit.api.organisation.model.Organisation;

import java.util.List;

/**
 * Dao для работы с организациями {@link Organisation}
 * @author Artyom Karkavin
 */
public interface OrganisationDao extends CrudRepository<Organisation, Long> {
    /**
     * Получение списка организаций с применением фильтров
     * @param name наименование
     * @param inn ИНН
     * @return список организаций
     * @see Organisation
     */
    @Query("SELECT o FROM Organisation o WHERE " +
            "   LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.inn LIKE CONCAT('%', :inn, '%')")
    List<Organisation> findByNameAndInn(@Param("name") String name,
                                        @Param("inn") String inn);

    /**
     * Получение списка организаций с применением фильтров
     * @param name наименование
     * @param inn ИНН
     * @param active статус (действующая ли)
     * @return список организаций
     * @see Organisation
     */
    @Query("SELECT o FROM Organisation o WHERE " +
            "   LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.inn LIKE CONCAT('%', :inn, '%') " +
            "   AND o.isActive = :active")
    List<Organisation> findByNameAndInnAndIsActive(@Param("name") String name,
                                                   @Param("inn") String inn,
                                                   @Param("active") boolean active);
}