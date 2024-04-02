package com.itm.space.backendresources.configuration;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS;
import static org.keycloak.OAuth2Constants.PASSWORD;

@Configuration
public class KeycloakClientConfiguration {
    @Value("${keycloak.credentials.secret}")
    private String secretKey;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.auth-server-url}")
    private String authUrl;
    @Value("${keycloak.realm}")
    private String realm;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(authUrl)
                .realm(realm)
                .grantType(CLIENT_CREDENTIALS)
                .clientId(clientId)
                .clientSecret(secretKey)
                .build();
    }
}
/*
Предоставленный код Java определяет конфигурационный класс Spring Boot с именем KeycloakClientConfiguration.
Он аннотирован @Configuration, что указывает на то, что это класс конфигурации для Spring Boot.

Этот класс отвечает за настройку клиента Keycloak, который используется для взаимодействия с сервером Keycloak.
Keycloak — это система управления доступом и удостоверениями с открытым исходным кодом.

Он использует аннотацию @Value из Spring Boot для внедрения значений из файла application.properties в поля класса.

Он определяет метод keycloak(), аннотированный @Bean. Этот метод используется для создания объекта Keycloak,
который является главной точкой входа для взаимодействия с сервером Keycloak.

Метод KeycloakBuilder.builder() используется для создания конструктора для объекта Keycloak.
Затем настраивается конструктор со следующими параметрами:
serverUrl(authUrl): устанавливает URL-адрес сервера Keycloak.
realm(realm): устанавливает царство для использования для аутентификации.
grantType(CLIENT_CREDENTIALS): устанавливает тип гранта для использования для аутентификации клиента.
clientId(clientId): устанавливает идентификатор клиента для использования для аутентификации.
clientSecret(secretKey): устанавливает секрет клиента для использования для аутентификации.
Наконец, для создания объекта Keycloak вызывается метод build() в конструкторе.

Этот класс конфигурации предоставляет способ простой настройки клиента Keycloak в приложении Spring Boot.
Объект Keycloak, созданный этим классом, можно использовать для выполнения различных операций,
связанных с аутентификацией пользователя, авторизацией и управлением токенами.
 */