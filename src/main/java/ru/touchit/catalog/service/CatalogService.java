package ru.touchit.catalog.service;

import ru.touchit.catalog.view.CountryView;
import ru.touchit.catalog.view.DocView;

import java.util.List;

public interface CatalogService {
    List<CountryView> getCountries();
    List<DocView> getDocs();
}