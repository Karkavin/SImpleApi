package ru.touchit.api.user.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.user.model.User;

/**
 * Dao для работы с сотрудниками {@link User}
 * @author Artyom Karkavin
 */
public interface UserDao extends CrudRepository<User, Long> {

}