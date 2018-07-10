package ru.touchit.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.touchit.catalog.model.Country;
import ru.touchit.catalog.response.CountryResponse;
import ru.touchit.catalog.response.DocResponse;
import ru.touchit.catalog.model.Doc;
import ru.touchit.catalog.service.CatalogService;
import ru.touchit.common.DataResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CatalogController {
    private final CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RequestMapping(value="/api/docs", method = RequestMethod.GET)
    public ResponseEntity<?> docs() {
        List<Doc> docs = catalogService.getDocs();

        return ResponseEntity.ok().body(
                new DataResponse<List<DocResponse>>(
                        docs.stream()
                                .map(doc -> new DocResponse(doc.getCode(), doc.getName()))
                                .collect(Collectors.toList())));
    }

    @RequestMapping(value="/api/countries", method = RequestMethod.GET)
    public ResponseEntity<?> countries() {
        List<Country> countries = catalogService.getCountries();

        return ResponseEntity.ok().body(
                new DataResponse<List<CountryResponse>>(
                        countries.stream()
                                .map(country -> new CountryResponse(country.getCode(), country.getName()))
                                .collect(Collectors.toList())));
    }
}