package ru.touchit.api.catalog.service;

import ru.touchit.api.catalog.view.CountryView;
import ru.touchit.api.catalog.view.DocView;

import java.util.List;

/**
 * Сервис для работы с каталогами (документы и страны)
 * @author Artyom Karkavin
 */
public interface CatalogService {
    /**
     * Получение всех объектов CountryView
     * @return список стран
     * @see CountryView
     */
    List<CountryView> getCountries();

    /**
     * Получение всех объектов DocView
     * @return список документов
     * @see DocView
     */
    List<DocView> getDocs();
}