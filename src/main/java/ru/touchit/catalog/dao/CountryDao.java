package ru.touchit.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.catalog.model.Country;

import java.util.List;

/**
 * Dao для работы с каталогами (страны)
 * @autor Artyom Karkavin
 */
public interface CountryDao extends CrudRepository<Country, Short> {
    /**
     * Получение всех объекты Country
     * @return список стран
     */
    List<Country> findAll();
}