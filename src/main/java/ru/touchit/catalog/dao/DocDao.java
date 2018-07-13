package ru.touchit.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.catalog.model.Doc;

import java.util.List;

/**
 * Dao для работы с каталогами (документы)
 * @autor Artyom Karkavin
 */
public interface DocDao extends CrudRepository<Doc, Short> {
    /**
     * Получить все объекты Doc
     * @return список документов
     */
    List<Doc> findAll();
}