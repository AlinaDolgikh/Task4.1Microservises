package com.itm.space.backendresources.controller;

import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @Secured("ROLE_MODERATOR")
    @SecurityRequirement(name = "oauth2_auth_code")
    public void create(@RequestBody @Valid UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_MODERATOR")
    @SecurityRequirement(name = "oauth2_auth_code")
    public UserResponse getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/hello")
    @Secured("ROLE_MODERATOR")
    @SecurityRequirement(name = "oauth2_auth_code")
    public String hello() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
/*
Предоставленный Java-код определяет класс контроллера Spring Boot с именем UserController.
Он помечен @RestController, что указывает на то, что это контроллер для обработки HTTP-запросов и ответов.
Он также помечен @RequestMapping("/api/users"), что указывает на то, что он обрабатывает запросы по пути "/api/users".
Этот класс отвечает за обработку операций, связанных с пользователем, таких как создание пользователя и
получение пользователя по его идентификатору. Он использует UserService для выполнения реальных операций.
Метод create() используется для создания нового пользователя. Он помечен @PostMapping, что указывает на то,
что он обрабатывает запросы POST. Он также помечен @Secured("ROLE_MODERATOR") и
@SecurityRequirement(имя = "oauth2_auth_code"), что указывает на то, что только пользователи с ролью "ROLE_MODERATOR"
могут получить доступ к этой конечной точке и что для аутентификации следует использовать тип предоставления
кода авторизации OAuth 2.0. Метод принимает в качестве входных данных объект UserRequest,
который содержит пользовательские данные.

Метод getUserById() используется для определения пользователя по его идентификатору. Он помечен @GetMapping("/{id}"),
что указывает на то, что он обрабатывает запросы GET по пути "/{id}". Он также помечен @Secured("ROLE_MODERATOR") и
@SecurityRequirement(имя = "oauth2_auth_code"), что указывает на то, что только пользователи с ролью "ROLE_MODERATOR"
могут получить доступ к этой конечной точке и что для аутентификации следует использовать тип предоставления кода
авторизации OAuth 2.0. Метод принимает UUID в качестве входных данных, который является идентификатором пользователя
для извлечения.

Метод hello() - это простой метод, который возвращает имя пользователя, прошедшего проверку подлинности в данный момент.
Он помечен @GetMapping("/hello"), что указывает на то, что он обрабатывает запросы GET по пути "/hello".
Он также помечен @Secured("ROLE_MODERATOR") и @SecurityRequirement(имя = "oauth2_auth_code"), что указывает на то,
что только пользователи с ролью "ROLE_MODERATOR" могут получить доступ к этой конечной точке и что для аутентификации
следует использовать тип предоставления кода авторизации OAuth 2.0.

Этот класс контроллера обеспечивает безопасное выполнение операций, связанных с пользователем. Он использует аннотации
Spring Security для ограничения доступа к определенным конечным точкам на основе ролей пользователей и механизмов
аутентификации.
 */