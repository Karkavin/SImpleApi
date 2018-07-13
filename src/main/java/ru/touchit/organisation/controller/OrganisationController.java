package ru.touchit.organisation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.touchit.common.DataResponse;
import ru.touchit.common.ErrorResponse;
import ru.touchit.common.ResultResponse;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.organisation.view.BaseOrganisationView;
import ru.touchit.organisation.service.OrganisationService;
import ru.touchit.organisation.view.FilterOrganisationView;
import ru.touchit.organisation.view.FilterResultOrganisationView;
import ru.touchit.organisation.view.FullOrganisationView;
import ru.touchit.util.BindingResultParser;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с организациями
 * @autor Artyom Karkavin
 */
@RestController
public class OrganisationController {
    private final OrganisationService organisationService;

    /**
     * Конструктор
     * @param organisationService - сервис для работы с данными
     */
    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    }

    /**
     * Получение подробной информации об организации
     * @param id идентификатор организации
     * @return data: подробная информация об организации
     */
    @RequestMapping(value="/api/organisation/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@PathVariable Long id) {
        FullOrganisationView organisationView;

        try {
            organisationView = organisationService.getById(id);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(organisationView));
    }

    /**
     * Создание новой организации
     * @param organisationView JSON (без id)
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     */
    @RequestMapping(value="/api/organisation/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody @Valid BaseOrganisationView organisationView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        organisationService.add(organisationView);

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Обновление организации
     * @param organisationView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     */
    @RequestMapping(value="/api/organisation/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody @Valid FullOrganisationView organisationView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            organisationService.update(organisationView);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Получение списка организаций с применением фильтров
     * @param organisationView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: список организаций
     */
    @RequestMapping(value="/api/organisation/list", method = RequestMethod.POST)
    public ResponseEntity<?> filter(@RequestBody @Valid FilterOrganisationView organisationView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        List<FilterResultOrganisationView> organisationViews = organisationService.filter(organisationView);

        return ResponseEntity.ok().body(new DataResponse<>(organisationViews));
    }
}