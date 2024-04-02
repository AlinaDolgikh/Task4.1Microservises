package com.itm.space.backendresources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseIntegrationTest {

    private final ObjectWriter contentWriter = new ObjectMapper()
            .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
            .writer()
            .withDefaultPrettyPrinter();

    @Autowired
    protected MockMvc mvc;

    protected MockHttpServletRequestBuilder requestToJson(MockHttpServletRequestBuilder requestBuilder) {
        return requestBuilder
                .contentType(APPLICATION_JSON);
    }

    protected MockHttpServletRequestBuilder requestWithContent(MockHttpServletRequestBuilder requestBuilder,
                                                               Object content) throws JsonProcessingException {
        return requestToJson(requestBuilder).content(contentWriter.writeValueAsString(content));
    }
}
/*
Предоставленный Java-код определяет абстрактный тестовый класс с именем BaseIntegrationTest для интеграционных тестов
Spring Boot. В нем используются аннотации @SpringBootTest и @AutoConfigureMockMvc для настройки контекста
приложения Spring и макетных контроллеров MVC для тестирования.

Этот класс содержит следующие поля и методы:

contentWriter: объект ObjectWriter, который используется для преобразования объектов в строки JSON.
Он настроен таким образом, чтобы отключить перенос значения root в выходные данные JSON.

mvc: объект MockMvc, который используется для отправки фиктивных HTTP-запросов тестируемым контроллерам.

requestToJson(): метод, который использует MockHttpServletRequestBuilder и присваивает заголовку Content-Type значение application/json.

requestWithContent(): метод, который принимает MockHttpServletRequestBuilder, объект, который должен быть преобразован в JSON, и устанавливает заголовок Content-Type в application/json. Он использует contentWriter для преобразования объекта в строку JSON и устанавливает ее в качестве тела запроса.

Этот базовый интеграционный тестовый класс предоставляет общие настройки и вспомогательные методы для написания интеграционных тестов для контроллеров Spring Boot. Используя этот базовый класс, тестовые классы могут легко отправлять имитационные HTTP-запросы и подтверждать результаты
 */