package ru.touchit.catalog.service;

import ru.touchit.catalog.view.CountryView;
import ru.touchit.catalog.view.DocView;

import java.util.List;

/**
 * Сервис для работы с каталогами (документы и страны)
 * @autor Artyom Karkavin
 */
public interface CatalogService {
    /**
     * Получение всех объектов CountryView
     * @return список стран
     */
    List<CountryView> getCountries();

    /**
     * Получение всех объектов DocView
     * @return список документов
     */
    List<DocView> getDocs();
}