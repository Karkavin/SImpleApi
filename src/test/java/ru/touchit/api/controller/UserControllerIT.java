package ru.touchit.api.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.touchit.api.common.DataResponse;
import ru.touchit.api.common.ErrorResponse;
import ru.touchit.api.common.ResultResponse;
import ru.touchit.api.user.view.BaseUserView;
import ru.touchit.api.user.view.FilterResultUserView;
import ru.touchit.api.user.view.FilterUserView;
import ru.touchit.api.user.view.FullUserView;

import java.io.IOException;
import java.util.List;

/**
 * Интеграционные тесты для контроллера сотрудников
 * @author Artyom Karkavin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerIT extends BaseControllerIT {
    /**
     * Тестирование получения подробной информации о сотруднике по id
     */
    @Test
    public void testDetailUser() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/1"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        try {
            DataResponse<FullUserView> resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, FullUserView.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование получения подробной информации о сотруднике с id отсутсвующего сотрудника
     */
    @Test
    public void testDetailUserWithNoUser() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/0"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        try {
            ErrorResponse resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование сохранения данных о сотруднике
     */
    @Test
    public void testSaveValidUser() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setSecondName("Домрычев");
        user.setMiddleName("Сергеевич");
        user.setPosition("Генеральный директор");
        user.setPhone("89271837495");
        user.setCitizenshipCode((short) 643);
        user.setDocCode((short) 21);
        user.setDocNumber("6316301232");
        user.setDocDate("12-21-1990");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<ResultResponse> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, ResultResponse.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getData().getResult().equals("Success"));
    }

    /**
     * Тестирование сохранения данных о сотруднике со значением null в необязательных полях
     */
    @Test
    public void testSaveValidUserWithNullInNotRequiredParameter() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<ResultResponse> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, ResultResponse.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getData().getResult().equals("Success"));
    }

    /**
     * Тестирование сохранения данных о сотруднике с пропущенным обязательным параметром firstName
     */
    @Test
    public void testSaveUserWithoutOneRequiredParameter() {
        BaseUserView user = new BaseUserView();
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("firstName"));
    }

    /**
     * Тестирование сохранения данных о сотруднике в отсутствующую организацию
     */
    @Test
    public void testSaveUserInOrganisationThatNotExists() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(0);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("orgId"));
    }

    /**
     * Тестирование сохранения данных о сотруднике в отсутствующий офис
     */
    @Test
    public void testSaveUserInOfficeThatNotExists() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(0);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("offId"));
    }

    /**
     * Тестирование сохранения данных о сотруднике в офис не пренадлежащий организации
     */
    @Test
    public void testSaveUserInOrganisationWithOtherOffice() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(6);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", (resultData.getError().contains("orgId") && resultData.getError().contains("offId")));
    }

    /**
     * Тестирование сохранения данных о сотруднике с неверным форматом citizenshipCode
     */
    @Test
    public void testSaveUserWithInvalidCitizenshipCode() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setCitizenshipCode((short)1001);
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("citizenshipCode"));
    }

    /**
     * Тестирование сохранения данных о сотруднике с неверным форматом docCode
     */
    @Test
    public void testSaveUserWithInvalidDocCode() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setDocCode((short)101);
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("docCode"));
    }

    /**
     * Тестирование сохранения данных о сотруднике с неверным форматом даты docDate
     */
    @Test
    public void testSaveUserWithInvalidDocDate() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setDocDate("21-21-1996");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("docDate"));
    }

    /**
     * Тестирование сохранения данных о сотруднике с отсутствующим docCode
     */
    @Test
    public void testSaveUserWithDocCodeThatNotExists() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setDocCode((short)20);
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("docCode"));
    }

    /**
     * Тестирование сохранения данных о сотруднике с отсутствующим citizenshipCode
     */
    @Test
    public void testSaveUserWithCitizenshipCodeThatNotExists() {
        BaseUserView user = new BaseUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setCitizenshipCode((short)999);
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<BaseUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/save"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("citizenshipCode"));
    }

    /**
     * Тестирование обновления данных о сотруднике
     */
    @Test
    public void testUpdateValidUser() {
        FullUserView user = new FullUserView();
        user.setId(1);
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<FullUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/update"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<ResultResponse> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, ResultResponse.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование обновления данных о сотруднике с пропущенным обязательным id сотрудника
     */
    @Test
    public void testUpdateUserWithoutId() {
        FullUserView user = new FullUserView();
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(1);
        HttpEntity<FullUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/update"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("id"));
    }

    /**
     * Тестирование обновления данных о сотруднике: переход сотрудника в другую организацию, в которой есть данный офис
     */
    @Test
    public void testUpdateMoveUserToOtherOrganisationWithValidOffice() {
        FullUserView user = new FullUserView();
        user.setId(1);
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(3);
        user.setOffId(6);
        HttpEntity<FullUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/update"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<ResultResponse> resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, ResultResponse.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getData().getResult().contains("Success"));
    }

    /**
     * Тестирование обновления данных о сотруднике: переход сотрудника в другую организацию, в которой нет данного офиса
     */
    @Test
    public void testUpdateMoveUserToOtherOrganisationWithInvalidOffice() {
        FullUserView user = new FullUserView();
        user.setId(1);
        user.setFirstName("Иван");
        user.setPosition("Генеральный директор");
        user.setOrgId(1);
        user.setOffId(6);
        HttpEntity<FullUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/update"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("orgId") && resultData.getError().contains("offId"));
    }

    /**
     * Тестирование применения фильтров при поиске сотрудников
     */
    @Test
    public void testFilterValidUser() {
        FilterUserView user = new FilterUserView();
        user.setOffId(1);
        user.setFirstName("Иван");
        user.setSecondName("Домрычев");
        user.setMiddleName("Сергеевич");
        user.setPosition("Генеральный директор");
        user.setDocCode((short) 21);
        user.setCitizenshipCode((short) 643);
        HttpEntity<FilterUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<List<FilterResultUserView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultUserView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске сотрудников со значением null в необязательных полях
     */
    @Test
    public void testFilterValidUserWithNullInNotRequiredParameter() {
        FilterUserView user = new FilterUserView();
        user.setOffId(1);
        HttpEntity<FilterUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<List<FilterResultUserView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultUserView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске сотрудников с пропущенным обязательным параметром offId
     */
    @Test
    public void testFilterValidUserWithoutRequiredParameterOffId() {
        FilterUserView user = new FilterUserView();
        HttpEntity<FilterUserView> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/user/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY);

        boolean isTypeValid = true;
        ErrorResponse resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(), ErrorResponse.class);
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);

        Assert.assertTrue("Результат операции", resultData.getError().contains("offId"));
    }
}