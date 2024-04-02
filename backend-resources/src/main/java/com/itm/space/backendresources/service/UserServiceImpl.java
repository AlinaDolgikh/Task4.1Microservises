package com.itm.space.backendresources.service;

import com.itm.space.backendresources.api.request.UserRequest;
import com.itm.space.backendresources.api.response.UserResponse;
import com.itm.space.backendresources.exception.BackendResourcesException;
import com.itm.space.backendresources.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final Keycloak keycloakClient;
    private final UserMapper userMapper;

    @Value("${keycloak.realm}")
    private String realm;

    public void createUser(UserRequest userRequest) {
        CredentialRepresentation password = preparePasswordRepresentation(userRequest.getPassword());
        UserRepresentation user = prepareUserRepresentation(userRequest, password);
        try {
            Response response = keycloakClient.realm(realm).users().create(user);
            String userId = CreatedResponseUtil.getCreatedId(response);
            log.info("Created UserId: {}", userId);
        } catch (WebApplicationException ex) {
            log.error("Exception on \"createUser\": ", ex);
            throw new BackendResourcesException(ex.getMessage(), HttpStatus.resolve(ex.getResponse().getStatus()));
        }
    }

    @Override
    public UserResponse getUserById(UUID id) {
        UserRepresentation userRepresentation;
        List<RoleRepresentation> userRoles;
        List<GroupRepresentation> userGroups;
        try {
            userRepresentation = keycloakClient.realm(realm).users().get(String.valueOf(id)).toRepresentation();
            userRoles = keycloakClient.realm(realm)
                    .users().get(String.valueOf(id)).roles().getAll().getRealmMappings();
            userGroups = keycloakClient.realm(realm).users().get(String.valueOf(id)).groups();
        } catch (RuntimeException ex) {
            log.error("Exception on \"getUserById\": ", ex);
            throw new BackendResourcesException(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userMapper.userRepresentationToUserResponse(userRepresentation, userRoles, userGroups);
    }

    private CredentialRepresentation preparePasswordRepresentation(String password) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        return credentialRepresentation;
    }

    private UserRepresentation prepareUserRepresentation(UserRequest userRequest,
                                                         CredentialRepresentation credentialRepresentation) {
        UserRepresentation newUser = new UserRepresentation();
        newUser.setUsername(userRequest.getUsername());
        newUser.setEmail(userRequest.getEmail());
        newUser.setCredentials(List.of(credentialRepresentation));
        newUser.setEnabled(true);
        newUser.setFirstName(userRequest.getFirstName());
        newUser.setLastName(userRequest.getLastName());
        return newUser;
    }
}
/*
Предоставленный Java-код определяет класс службы Spring Boot с именем UserServiceImpl. Он реализует интерфейс UserService
и помечен символами @Slf4j, @Service и @RequiredArgsConstructor.
Этот служебный класс отвечает за управление пользователями, использующими Java-клиент Keycloak. У него есть два поля:
keycloakClient: Объект Keycloak, представляющий клиент Keycloak.
userMapper: объект UserMapper, который используется для сопоставления между объектами UserRepresentation и UserResponse.
Этот служебный класс определяет два метода:
CreateUser(): Этот метод используется для создания нового пользователя в Keycloak. В качестве входных данных
он принимает объект UserRequest, который содержит данные пользователя. Метод подготавливает объектCredentialRepresentation
для пароля пользователя и объект UserRepresentation для других данных пользователя. Затем он использует клиент
Keycloak для создания пользователя в Keycloak.
getUserById(): Этот метод используется для определения пользователя по его идентификатору.
В качестве входных данных используется UUID, который является идентификатором пользователя для извлечения.
Метод использует клиент Keycloak для получения представления пользователя, ролей и групп из Keycloak.
Затем он использует userMapper для сопоставления объекта UserRepresentation с объектом UserResponse.

Этот класс обслуживания предоставляет способ создания и извлечения пользователей из Keycloak удобным и
типобезопасным способом.
 */
