package ru.touchit.office.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.touchit.office.model.Office;
import ru.touchit.organisation.model.Organisation;

import java.util.List;

public interface OfficeDao extends CrudRepository<Office, Long> {
    @Query("SELECT o FROM Office o WHERE " +
            "   o.organisation = :organisation " +
            "   AND LOWER(o.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "   AND o.phone LIKE CONCAT('%', :phone, '%')")
    List<Office> findByOrgIdAndNameAndPhone(@Param("organisation") Organisation organisation,
                                            @Param("name") String name,
                                            @Param("phone") String phone);

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