package com.itm.space.backendresources.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BackendResourcesException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BackendResourcesException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
/*
Предоставленный Java-код определяет пользовательский класс исключений с именем Backend Resources Exception.
Он расширяет класс RuntimeException, указывая, что это непроверенное исключение.
Этот класс исключений используется для представления ошибок, возникающих при работе с внутренними ресурсами,
такими как база данных или внешний API. У него есть два поля:
сообщение: Это поле содержит сообщение об ошибке.
Статус http: Это поле содержит код состояния HTTP, связанный с ошибкой.
Конструктор этого класса принимает два параметра: message и http Status.
Он присваивает полю message значение предоставленного сообщения, а полю http Status значение предоставленного
кода состояния HTTP.
Этот класс исключений может использоваться контроллерами Spring Boot для обработки ошибок,
возникающих при взаимодействии с серверными ресурсами. Это позволяет контроллерам возвращать клиенту определенный
код состояния HTTP и сообщение об ошибке.
 */