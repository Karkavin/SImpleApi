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
import ru.touchit.api.organisation.model.Organisation;
import ru.touchit.api.organisation.view.BaseOrganisationView;
import ru.touchit.api.organisation.view.FilterOrganisationView;
import ru.touchit.api.organisation.view.FilterResultOrganisationView;
import ru.touchit.api.organisation.view.FullOrganisationView;

import java.io.IOException;
import java.util.List;

/**
 * Интеграционные тесты для контроллера организации
 * @author Artyom Karkavin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganisationControllerIT extends BaseControllerIT {
    /**
     * Тестирование получения подробной информации об организации по id
     */
    @Test
    public void testDetailOrganisation() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/1"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        try {
            DataResponse<FullOrganisationView> resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(DataResponse.class, FullOrganisationView.class));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование получения подробной информации об организации с id отсутствующей организации
     */
    @Test
    public void testDetailOrganisationWithNoOrganisation() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/0"),
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
     * Тестирование сохранения данных об организации
     */
    @Test
    public void testSaveValidOrganisation() {
        BaseOrganisationView organisation = new BaseOrganisationView(new Organisation("Art of Flowers", "LLC Art of Flowers",
                "1294516859", "345511225", "410015, Россия, г. Саратов, ул. Дзержинского, 12", "89279236287", true));
        HttpEntity<BaseOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/save"),
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
     * Тестирование сохранения данных об организации со значением null в необязательных полях
     */
    @Test
    public void testSaveValidOrganisationWithNullInNotRequiredParameter() {
        BaseOrganisationView organisation = new BaseOrganisationView(new Organisation("Art of Flowers", "LLC Art of Flowers",
                "1294516859", "345511225", "410015, Россия, г. Саратов, ул. Дзержинского, 12"));
        HttpEntity<BaseOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/save"),
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
     * Тестирование сохранения данных об организации с неверным форматом ИНН
     */
    @Test
    public void testSaveOrganisationWithInvalidInn() {
        BaseOrganisationView organisation = new BaseOrganisationView(new Organisation("Art of Flowers", "LLC Art of Flowers",
                "1", "345511225", "410015, Россия, г. Саратов, ул. Дзержинского, 12"));
        HttpEntity<BaseOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/save"),
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

        Assert.assertTrue("Результат операции", resultData.getError().contains("inn"));
    }

    /**
     * Тестирование сохранения данных об организации с неверным форматом КПП
     */
    @Test
    public void testSaveOrganisationWithInvalidKpp() {
        BaseOrganisationView organisation = new BaseOrganisationView(new Organisation("Art of Flowers", "LLC Art of Flowers",
                "1294516859", "1", "410015, Россия, г. Саратов, ул. Дзержинского, 12"));
        HttpEntity<BaseOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/save"),
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

        Assert.assertTrue("Результат операции", resultData.getError().contains("kpp"));
    }

    /**
     * Тестирование сохранения данных об организации с пропущенным обязательным параметром address
     */
    @Test
    public void testSaveOrganisationWithoutOneRequiredParameter() {
        BaseOrganisationView organisation = new BaseOrganisationView(new Organisation("Art of Flowers", "LLC Art of Flowers",
                "1294516859", "345511225",
                "410015, Россия, г. Саратов, ул. Дзержинского, 12"));
        organisation.setAddress(null);
        HttpEntity<BaseOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/save"),
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

        Assert.assertTrue("Результат операции", resultData.getError().contains("address"));
    }

    /**
     * Тестирование обновления данных об организации
     */
    @Test
    public void testUpdateValidOrganisation() {
        FullOrganisationView organisation = new FullOrganisationView();
        organisation.setId(1);
        organisation.setName("Art of Flowers");
        organisation.setFullName("LLC Art of Flowers");
        organisation.setInn("1294516859");
        organisation.setKpp("345511225");
        organisation.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        HttpEntity<FullOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/update"),
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
     * Тестирование обновления данных об организации с пропущенным обязательным id организации
     */
    @Test
    public void testUpdateOrganisationWithoutId() {
        FullOrganisationView organisation = new FullOrganisationView();
        organisation.setName("Art of Flowers");
        organisation.setFullName("LLC Art of Flowers");
        organisation.setInn("1294516859");
        organisation.setKpp("345511225");
        organisation.setAddress("410015, Россия, г. Саратов, ул. Дзержинского, 12");
        HttpEntity<FullOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/update"),
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
     * Тестирование применения фильтров при поиске организаций
     */
    @Test
    public void testFilterValidOrganisation() {
        FilterOrganisationView organisation = new FilterOrganisationView("art", "1345511225", true);
        HttpEntity<FilterOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);
        boolean isTypeValid = true;
        DataResponse<List<FilterResultOrganisationView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultOrganisationView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске организаций со значением null в необязательных полях
     */
    @Test
    public void testFilterValidOrganisationWithNullInNotRequiredParameter() {
        FilterOrganisationView organisation = new FilterOrganisationView();
        organisation.setName("art");
        HttpEntity<FilterOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/list"),
                HttpMethod.POST, entity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);
        boolean isTypeValid = true;
        DataResponse<List<FilterResultOrganisationView>> resultData = null;
        try {
            resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, FilterResultOrganisationView.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование применения фильтров при поиске организаций с пропущенным обязательным параметром name
     */
    @Test
    public void testFilterValidOrganisationWithoutRequiredParameterName() {
        FilterOrganisationView organisation = new FilterOrganisationView();
        HttpEntity<FilterOrganisationView> entity = new HttpEntity<>(organisation, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/organisation/list"),
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

        Assert.assertTrue("Результат операции", resultData.getError().contains("name"));
    }
}