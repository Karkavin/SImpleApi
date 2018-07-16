package ru.touchit.api.user.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.user.model.UserDoc;

/**
 * Dao для работы с документами сотрудников {@link UserDoc}
 * @author Artyom Karkavin
 */
public interface UserDocDao extends CrudRepository<UserDoc, Long> {

}