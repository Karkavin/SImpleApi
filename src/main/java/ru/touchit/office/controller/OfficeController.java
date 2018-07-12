package ru.touchit.office.controller;

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
import ru.touchit.office.exception.NoSuchOfficeException;
import ru.touchit.office.service.OfficeService;
import ru.touchit.office.view.BaseOfficeView;
import ru.touchit.office.view.FilterOfficeView;
import ru.touchit.office.view.FilterResultOfficeView;
import ru.touchit.office.view.FullOfficeView;
import ru.touchit.organisation.exception.NoSuchOrganisationException;
import ru.touchit.util.BindingResultParser;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OfficeController {
    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @RequestMapping(value="/api/office/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@PathVariable Long id) {
        try {
            FullOfficeView officeView = officeService.getById(id);

            return ResponseEntity.ok().body(new DataResponse<>(officeView));
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден"));
        }
    }

    @RequestMapping(value="/api/office/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody @Valid BaseOfficeView officeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            officeService.add(officeView);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    @RequestMapping(value="/api/office/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody @Valid FullOfficeView officeView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            officeService.update(officeView);
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден"));
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

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
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(officeViews));
    }
}