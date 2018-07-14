package ru.touchit.api.organisation.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.organisation.model.Organisation;

/**
 * Dao для работы с организациями {@link Organisation}
 * @author Artyom Karkavin
 */
public interface OrganisationDao extends CrudRepository<Organisation, Long> {

}