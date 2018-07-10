package ru.touchit.catalog.service;

import ru.touchit.catalog.model.Country;
import ru.touchit.catalog.model.Doc;

import java.util.List;

public interface CatalogService {
    List<Country> getCountries();
    List<Doc> getDocs();
}