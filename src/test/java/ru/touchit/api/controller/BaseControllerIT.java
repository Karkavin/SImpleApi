package ru.touchit.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Before;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

/**
 * Абстрактный класс с необходимыми для интеграционных тестов компонентами
 * @author Artyom Karkavin
 */
abstract class BaseControllerIT {
    /** Поле: порт */
    @LocalServerPort
    private int port;

    /** Поля: restTemplate, заголовок, базовый GET entity */
    TestRestTemplate restTemplate;
    HttpHeaders headers;
    HttpEntity<String> baseGetEntity;

    /** Поля: (Jackson) mapper и typeFactory для маппинга */
    ObjectMapper mapper;
    TypeFactory typeFactory;

    /**
     * Инициализация компонентов
     */
    @Before
    public void setup() {
        restTemplate = new TestRestTemplate();
        headers = new HttpHeaders();
        baseGetEntity = new HttpEntity<>(null, headers);
        mapper = new ObjectMapper();
        typeFactory = mapper.getTypeFactory();
    }

    /**
     * Генерация URL
     */
    String generateUrl(String uri) {
        return "http://localhost:" + port + uri;
    }
}