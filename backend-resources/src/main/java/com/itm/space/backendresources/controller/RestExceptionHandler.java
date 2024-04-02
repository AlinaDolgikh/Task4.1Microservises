package com.itm.space.backendresources.controller;

import com.itm.space.backendresources.exception.BackendResourcesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BackendResourcesException.class)
    public ResponseEntity<String> handleException(BackendResourcesException backendResourcesException) {
        return new ResponseEntity<>(backendResourcesException.getMessage(), backendResourcesException.getHttpStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }

}
/*
Предоставленный Java-код определяет класс обработчика исключений Spring Boot с именем RestExceptionHandler.
Он помечен @RestControllerAdvice, что указывает на то, что это класс рекомендаций контроллеру
для обработки исключений в контроллерах Spring MVC.
Этот класс определяет два метода обработки исключений.:
Метод HandleException() обрабатывает исключения типа BackendResourcesException.
Этот метод принимает BackendResourcesException в качестве входных данных и возвращает ResponseEntity с сообщением
об исключении и кодом состояния HTTP.

Метод handleInvalidArgument() обрабатывает исключения типа MethodArgumentNotValidException.
Этот метод помечен символом @ResponseStatus(HttpStatus.BAD_REQUEST), который указывает,
что он должен возвращать код состояния HTTP 400 (неверный запрос).
В качестве входных данных он принимает исключение MethodArgumentNotValidException и возвращает карту ошибок полей.

Этот класс обработчика исключений предоставляет централизованный способ обработки исключений в приложении Spring Boot.
Его можно использовать для обработки как пользовательских исключений (таких как BackendResourcesException),
так и стандартных исключений Spring MVC (таких как MethodArgumentNotValidException).
 */