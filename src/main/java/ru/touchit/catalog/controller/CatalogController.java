package ru.touchit.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.touchit.catalog.view.CountryView;
import ru.touchit.catalog.view.DocView;
import ru.touchit.catalog.service.CatalogService;
import ru.touchit.common.DataResponse;

import java.util.List;

@RestController
public class CatalogController {
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping(value="/api/docs", method = RequestMethod.GET)
    public ResponseEntity<?> docs() {
        List<DocView> docsView = catalogService.getDocs();

        return ResponseEntity.ok().body(new DataResponse<>(docsView));
    }

    @RequestMapping(value="/api/countries", method = RequestMethod.GET)
    public ResponseEntity<?> countries() {
        List<CountryView> countriesView = catalogService.getCountries();

        return ResponseEntity.ok().body(new DataResponse<>(countriesView));
    }
}