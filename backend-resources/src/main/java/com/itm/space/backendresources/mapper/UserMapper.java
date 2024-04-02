package com.itm.space.backendresources.mapper;

import com.itm.space.backendresources.api.response.UserResponse;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Collections.class)
public interface UserMapper {

    @Mapping(target = "roles", source = "roleList", qualifiedByName = "mapRoleRepresentationToString")
    @Mapping(target = "groups", source = "groupList", qualifiedByName = "mapGroupRepresentationToString")
    UserResponse userRepresentationToUserResponse(UserRepresentation userRepresentation,
                                                  List<RoleRepresentation> roleList,
                                                  List<GroupRepresentation> groupList);

    @Named("mapRoleRepresentationToString")
    default List<String> mapRoleRepresentationToString(List<RoleRepresentation> roleList) {
        return roleList.stream().map(RoleRepresentation::getName).toList();
    }

    @Named("mapGroupRepresentationToString")
    default List<String> mapGroupRepresentationToString(List<GroupRepresentation> groupList) {
        return groupList.stream().map(GroupRepresentation::getName).toList();
    }

}
/*
Предоставленный Java-код определяет интерфейс MapStruct mapper с именем UserMapper.
MapStruct - это библиотека для генерации кода, которая упрощает сопоставление между объектами Java.
Этот интерфейс сопоставления определяет метод, называемый userRepresentationToUserResponse(),
который сопоставляет объект UserRepresentation, а также два списка объектов RoleRepresentation
и GroupRepresentation, с объектом UserResponse.
Аннотации @Mapping в этом методе указывают, как поля объекта UserRepresentation и двух списков должны
быть сопоставлены с полями объекта UserResponse.
Кроме того, этот интерфейс mapper определяет два именованных метода отображения:
@Named("mapRoleRepresentationToString"): Этот метод преобразует список объектов RoleRepresentation в список строк.
Он извлекает имя каждого объекта RoleRepresentation и добавляет его в список строк.
@Named("mapGroupRepresentationToString"): Этот метод преобразует список объектов GroupRepresentation в список строк.
Он извлекает имя каждого объекта GroupRepresentation и добавляет его в список строк.
Эти именованные методы сопоставления используются методом userRepresentationToUserResponse() для сопоставления
roleList и groupList с полями ролей и групп объекта UserResponse соответственно.

Этот интерфейс сопоставления предоставляет удобный способ сопоставления между объектами UserRepresentation и
UserResponse, включая сопоставление ролей и групп со строками.
 */