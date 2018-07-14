package ru.touchit.api.catalog.dao;

import org.springframework.data.repository.CrudRepository;
import ru.touchit.api.catalog.model.Doc;

import java.util.List;

/**
 * Dao для работы с каталогами (документы {@link Doc})
 * @author Artyom Karkavin
 */
public interface DocDao extends CrudRepository<Doc, Short> {
    /**
     * Получить все объекты Doc
     * @return список документов
     * @see Doc
     */
    List<Doc> findAll();
}