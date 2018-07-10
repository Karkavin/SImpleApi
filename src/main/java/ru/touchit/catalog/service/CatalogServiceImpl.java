package ru.touchit.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.touchit.catalog.dao.CountryDao;
import ru.touchit.catalog.dao.DocDao;
import ru.touchit.catalog.model.Country;
import ru.touchit.catalog.model.Doc;

import java.util.List;

@Service("catalogService")
@Transactional
public class CatalogServiceImpl implements CatalogService {
    CountryDao countryDao;
    DocDao docDao;

    @Autowired
    public CatalogServiceImpl(CountryDao countryDao, DocDao docDao) {
        this.countryDao = countryDao;
        this.docDao = docDao;
    }

    @Override
    public List<Country> getCountries() {
        return countryDao.findAll();
    }

    @Override
    public List<Doc> getDocs() {
        return docDao.findAll();
    }
}