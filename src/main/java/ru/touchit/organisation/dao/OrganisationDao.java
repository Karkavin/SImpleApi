package ru.touchit.organisation.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.touchit.organisation.model.Organisation;

import java.util.List;

public interface OrganisationDao extends CrudRepository<Organisation, Long> {
    @Query("SELECT o FROM Organisation o WHERE " +
            "   LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.inn LIKE CONCAT('%', :inn, '%')")
    List<Organisation> findByNameAndInn(@Param("name") String name,
                                        @Param("inn") String inn);

    @Query("SELECT o FROM Organisation o WHERE " +
            "   LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.inn LIKE CONCAT('%', :inn, '%') " +
            "   AND o.isActive = :active")
    List<Organisation> findByNameAndInnAndIsActive(@Param("name") String name,
                                                   @Param("inn") String inn,
                                                   @Param("active") boolean active);
}