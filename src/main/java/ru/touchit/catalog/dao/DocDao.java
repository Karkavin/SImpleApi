package ru.touchit.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.catalog.model.Doc;

import java.util.List;

public interface DocDao extends CrudRepository<Doc, Short> {
    List<Doc> findAll();
}