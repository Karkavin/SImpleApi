package ru.touchit.api.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.catalog.model.Country;

import java.util.List;

/**
 * Dao для работы с каталогами (страны {@link Country})
 * @author Artyom Karkavin
 */
public interface CountryDao extends CrudRepository<Country, Short> {
    /**
     * Получение всех объекты Country
     * @return список стран
     * @see Country
     */
    List<Country> findAll();
}