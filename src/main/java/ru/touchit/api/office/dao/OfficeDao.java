package ru.touchit.api.office.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.office.model.Office;

/**
 * Dao для работы с офисами {@link Office}
 * @author Artyom Karkavin
 */
public interface OfficeDao extends CrudRepository<Office, Long> {

}