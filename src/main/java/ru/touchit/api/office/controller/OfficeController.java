package ru.touchit.api.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.touchit.api.common.DataResponse;
import ru.touchit.api.common.ErrorResponse;
import ru.touchit.api.common.ResultResponse;
import ru.touchit.api.office.exception.NoSuchOfficeException;
import ru.touchit.api.office.model.Office;
import ru.touchit.api.office.service.OfficeService;
import ru.touchit.api.office.view.BaseOfficeView;
import ru.touchit.api.office.view.FilterOfficeView;
import ru.touchit.api.office.view.FilterResultOfficeView;
import ru.touchit.api.office.view.FullOfficeView;
import ru.touchit.api.organisation.exception.NoSuchOrganisationException;
import ru.touchit.util.BindingResultParser;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с офисами {@link Office}
 * @author Artyom Karkavin
 */
@RestController
public class OfficeController {
    private final OfficeService officeService;

    /**
     * Конструктор
     * @param officeService - сервис для работы с данными
     */
    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    /**
     * Получение подробной информации об офисе
     * @param id идентификатор офиса
     * @return data: подробная информация об офисе
     * @see FullOfficeView
     */
    @RequestMapping(value="/api/office/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@PathVariable Long id) {
        try {
            FullOfficeView officeView = officeService.getById(id);

            return ResponseEntity.ok().body(new DataResponse<>(officeView));
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден (поле id)"));
        }
    }

    /**
     * Добавление нового офиса для организации
     * @param officeView JSON (без id)
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     * @see BaseOfficeView
     */
    @RequestMapping(value="/api/office/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody @Valid BaseOfficeView officeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            officeService.add(officeView);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена (поле orgId)"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Обновление офиса
     * @param officeView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     * @see FullOfficeView
     */
    @RequestMapping(value="/api/office/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody @Valid FullOfficeView officeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            officeService.update(officeView);
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден (поле id)"));
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена (поле orgId)"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Получение списка офисов организации с применением фильтров
     * @param officeView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: список офисов организации
     * @see FilterOfficeView
     * @see FilterResultOfficeView
     */
    @RequestMapping(value="/api/office/list", method = RequestMethod.POST)
    public ResponseEntity<?> filter(@RequestBody @Valid FilterOfficeView officeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        List<FilterResultOfficeView> officeViews;
        try {
            officeViews = officeService.filter(officeView);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена (поле orgId)"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(officeViews));
    }
}