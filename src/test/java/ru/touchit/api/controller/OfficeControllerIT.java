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
import ru.touchit.api.office.view.BaseOfficeView;
import ru.touchit.api.office.view.FilterOfficeView;
import ru.touchit.api.office.view.FilterResultOfficeView;
import ru.touchit.api.office.view.FullOfficeView;

import java.io.IOException;
import java.util.List;

/**
 * Интеграционные тесты для контроллера офисов
 * @author Artyom Karkavin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficeControllerIT extends BaseControllerIT {
    /**
     * Тестирование получения подробной информации об офисе по id
     */
    @Test
    public void testDetailOffice() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/1"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        try {
            DataResponse<FullOfficeView> resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, FullOfficeView.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование получения подробной информации об офисе с id отсутствующего офиса
     */
    @Test
    public void testDetailOfficeWithNoOffice() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/0"),
                HttpMethod.GET, baseGetEntity, String.class);

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
     * Тестирование сохранения данных об офисе
     */
    @Test
    public void testSaveValidOffice() {
        BaseOfficeView office = new BaseOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setPhone("89279236287");
        office.setOrgId(1);
        office.setIsActive(true);
        HttpEntity<BaseOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/save"),
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
     * Тестирование сохранения данных об офисе со значением null в необязательных полях
     */
    @Test
    public void testSaveValidOfficeWithNullInNotRequiredParameter() {
        BaseOfficeView office = new BaseOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setOrgId(1);
        HttpEntity<BaseOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/save"),
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
     * Тестирование сохранения данных об офисе с отсутствующим значением id организации (orgId)
     */
    @Test
    public void testSaveOfficeWithNoOrganisationId() {
        BaseOfficeView office = new BaseOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        HttpEntity<BaseOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/save"),
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
     * Тестирование сохранения данных об офисе с id отсутсвующей организации
     */
    @Test
    public void testSaveOfficeWithOrganisationIdThanNotExists() {
        BaseOfficeView office = new BaseOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setOrgId(0);
        HttpEntity<BaseOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/save"),
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
     * Тестирование обновления данных об офисе
     */
    @Test
    public void testUpdateValidOffice() {
        FullOfficeView office = new FullOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setPhone("89279236287");
        office.setOrgId(1);
        office.setId(1);
        HttpEntity<FullOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/update"),
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
     * Тестирование обновления данных об офисе с пропущенным обязательным id офиса
     */
    @Test
    public void testUpdateOfficeWithoutId() {
        FullOfficeView office = new FullOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setPhone("89279236287");
        office.setOrgId(1);
        HttpEntity<FullOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/update"),
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
     * Тестирование обновления данных об офисе: переход офиса из одной организации в другую
     */
    @Test
    public void testUpdateMoveOfficeToAnotherOrganisation() {
        FullOfficeView office = new FullOfficeView();
        office.setName("Head office");
        office.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        office.setPhone("89279236287");
        office.setOrgId(1);
        office.setId(3);
        HttpEntity<FullOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/update"),
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
     * Тестирование применения фильтров при поиске офисов
     */
    @Test
    public void testFilterValidOffice() {
        FilterOfficeView office = new FilterOfficeView();
        office.setOrgId(1);
        office.setName("Head office");
        office.setPhone("89271236171");
        office.setIsActive(true);
        HttpEntity<FilterOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<List<FilterResultOfficeView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultOfficeView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске офисов со значением null в необязательных полях
     */
    @Test
    public void testFilterValidOfficeWithNullInNotRequiredParameter() {
        FilterOfficeView office = new FilterOfficeView();
        office.setOrgId(1);
        HttpEntity<FilterOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        DataResponse<List<FilterResultOfficeView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultOfficeView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске офисов с пропущенным обязательным параметром orgId
     */
    @Test
    public void testFilterValidOfficeWithoutRequiredParameterOrgId() {
        FilterOfficeView office = new FilterOfficeView();
        HttpEntity<FilterOfficeView> entity = new HttpEntity<>(office, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/office/list"),
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
}