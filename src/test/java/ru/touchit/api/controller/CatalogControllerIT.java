package ru.touchit.api.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.touchit.api.catalog.model.Country;
import ru.touchit.api.catalog.model.Doc;
import ru.touchit.api.common.DataResponse;

import java.io.IOException;
import java.util.List;

/**
 * Интеграционные тесты для контроллера каталога (документы и страны)
 * @author Artyom Karkavin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogControllerIT extends BaseControllerIT {
    /**
     * Тестирование получения списка документов
     */
    @Test
    public void testDocs() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/docs"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        try {
            DataResponse<List<Doc>> resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, Doc.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }

    /**
     * Тестирование получения списка стран
     */
    @Test
    public void testCountries() {
        ResponseEntity<String> response = restTemplate.exchange(
                generateUrl("/api/countries"),
                HttpMethod.GET, baseGetEntity, String.class);

        Assert.assertTrue("Результат запроса", response.getStatusCode() == HttpStatus.OK);

        boolean isTypeValid = true;
        try {
            DataResponse<List<Country>> resultData = mapper.readValue(
                    response.getBody(),
                    typeFactory.constructParametricType(
                            DataResponse.class,
                            typeFactory.constructCollectionType(List.class, Country.class)));
        } catch (IOException e) {
            isTypeValid = false;
        }
        Assert.assertTrue("Валидность типа данных результата запроса", isTypeValid);
    }
}