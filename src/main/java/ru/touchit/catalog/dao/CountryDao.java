package ru.touchit.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.catalog.model.Country;

import java.util.List;

public interface CountryDao extends CrudRepository<Country, Short> {
    List<Country> findAll();
}