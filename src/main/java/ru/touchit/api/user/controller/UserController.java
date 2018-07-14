package ru.touchit.api.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.touchit.api.catalog.exception.NoSuchCountryException;
import ru.touchit.api.catalog.exception.NoSuchDocException;
import ru.touchit.api.common.DataResponse;
import ru.touchit.api.common.ErrorResponse;
import ru.touchit.api.common.ResultResponse;
import ru.touchit.api.office.exception.NoSuchOfficeException;
import ru.touchit.api.organisation.exception.NoSuchOrganisationException;
import ru.touchit.api.user.exception.IncorrectDateException;
import ru.touchit.api.user.exception.NoSuchUserException;
import ru.touchit.api.office.exception.OfficeDoesNotInOrganisationException;
import ru.touchit.api.user.model.User;
import ru.touchit.api.user.service.UserService;
import ru.touchit.api.user.view.BaseUserView;
import ru.touchit.api.user.view.FilterResultUserView;
import ru.touchit.api.user.view.FilterUserView;
import ru.touchit.api.user.view.FullUserView;
import ru.touchit.util.BindingResultParser;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для работы с сотрудниками {@link User}
 * @author Artyom Karkavin
 */
@RestController
public class UserController {
    private final UserService userService;

    /**
     * Конструктор
     * @param userService - сервис для работы с данными
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение подробной информации о сотруднике
     * @param id идентификатор сотрудника
     * @return data: подробная информация о сотруднике
     * @see FullUserView
     */
    @RequestMapping(value="/api/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detail(@PathVariable Long id) {
        try {
            FullUserView userView = userService.getById(id);

            return ResponseEntity.ok().body(new DataResponse<>(userView));
        } catch (NoSuchUserException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Пользователь не был найден"));
        }
    }

    /**
     * Добавление нового сотрудника в офис организации
     * @param userView JSON (без id)
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     * @see BaseUserView
     */
    @RequestMapping(value="/api/user/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody @Valid BaseUserView userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            userService.add(userView);
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден"));
        } catch (IncorrectDateException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Неверный формат даты (требуется: MM-dd-yyyy)"));
        } catch (NoSuchDocException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код документа отсутствует в базе"));
        } catch (NoSuchCountryException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код страны отсутствует в базе"));
        } catch (OfficeDoesNotInOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Данного офиса не существует в указанной организации"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Обновление данных сотрудника
     * @param userView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: результат операции
     * @see FullUserView
     */
    @RequestMapping(value="/api/user/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody @Valid FullUserView userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        try {
            userService.update(userView);
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден"));
        } catch (NoSuchOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Организация не была найдена"));
        } catch (NoSuchUserException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Пользователь не был найден"));
        } catch (IncorrectDateException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Неверный формат даты (требуется: MM-dd-yyyy)"));
        } catch (NoSuchCountryException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код страны отсутствует в базе"));
        } catch (NoSuchDocException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код документа отсутствует в базе"));
        } catch (OfficeDoesNotInOrganisationException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Данного офиса не существует в указанной организации"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(new ResultResponse("Success")));
    }

    /**
     * Получение списка сотрудников офиса в организации с применением фильтров
     * @param userView JSON
     * @param bindingResult информация о результатах валидации JSON
     * @return data: список сотрудников офиса в организации
     * @see FilterResultUserView
     * @see FilterUserView
     */
    @RequestMapping(value="/api/user/list", method = RequestMethod.POST)
    public ResponseEntity<?> filter(@RequestBody @Valid FilterUserView userView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultParser.parseBindingResultError(bindingResult);

            return ResponseEntity.unprocessableEntity().body(new ErrorResponse(errorMessage));
        }

        List<FilterResultUserView> userViews;
        try {
             userViews = userService.filter(userView);
        } catch (NoSuchOfficeException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Офис не был найден"));
        } catch (NoSuchDocException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код документа отсутствует в базе"));
        } catch (NoSuchCountryException e) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Код страны отсутствует в базе"));
        }

        return ResponseEntity.ok().body(new DataResponse<>(userViews));
    }
}