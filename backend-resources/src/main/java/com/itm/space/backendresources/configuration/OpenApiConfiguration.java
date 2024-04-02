package com.itm.space.backendresources.configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "oauth2_auth_code",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "http://backend-keycloak-auth:8080/auth/realms/ITM/protocol/openid-connect/auth",
                        tokenUrl = "http://backend-keycloak-auth:8080/auth/realms/ITM/protocol/openid-connect/token",
                        scopes = {
                                @OAuthScope(name = "openid", description = "Read access")
                        }
                )
        ),
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfiguration {

    @Bean
    public OpenAPI publicApi() {
        return new OpenAPI();
    }
}
/*
Предоставленный Java-код определяет класс конфигурации Spring Boot с именем OpenApiConfiguration.
Он помечен символом @Configuration, что указывает на то, что это класс конфигурации для Spring Boot.
Этот класс отвечает за настройку OpenAPI, который является спецификацией для описания REST API.
В нем используется аннотация @SecurityScheme для определения схемы безопасности
для типа предоставления кода авторизации OAuth 2.0.
Аннотация @SecurityScheme используется для определения схемы безопасности с именем "oauth2_auth_code".
Эта схема безопасности определяет, что для аутентификации будет использоваться тип предоставления кода авторизации OAuth 2.0.
Поле type в аннотации @SecurityScheme имеет значение SecuritySchemeType.OAUTH2, что указывает на то,
что это схема безопасности OAuth 2.0.
Поле потоки в аннотации @SecurityScheme имеет значение объекта @OAuthFlows, который определяет потоки OAuth 2.0,
поддерживаемые этой схемой безопасности. В этом случае поддерживается только поток кода авторизации.
Поле authorizationCode объекта @OAuthFlows имеет значение объекта @OAuthFlow, который определяет поток кода авторизации.
Полям authorizationUrl и tokenUrl объекта @OAuthFlow присваиваются URL-адреса конечной точки авторизации и
конечной точки токена поставщика OAuth 2.0 соответственно.
Поле scopes объекта @OAuthFlow задано как массив объектов @OAuthScope, которые определяют области,
поддерживаемые этой схемой безопасности. В этом случае поддерживается только область "OpenID".
Поле in в аннотации @SecurityScheme имеет значение SecuritySchemeIn.
Заголовок, указывающий, что токен доступа OAuth 2.0 должен быть передан в заголовке авторизации HTTP-запроса.
Метод publicApi() помечен символом @Bean. Этот метод создает и возвращает объект OpenAPI,
который представляет спецификацию OpenAPI для REST API.
Этот класс конфигурации предоставляет способ простой настройки безопасности OpenAPI и OAuth 2.0 для приложения Spring Boot.
Спецификация OpenAPI, созданная с помощью этого класса, может использоваться клиентами API для понимания возможностей
REST API и способов безопасного взаимодействия с ним.
 */