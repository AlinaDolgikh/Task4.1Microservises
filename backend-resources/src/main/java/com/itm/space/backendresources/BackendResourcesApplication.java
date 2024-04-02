package com.itm.space.backendresources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendResourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendResourcesApplication.class, args);
    }

}
/*
Предоставленный Java-код определяет класс приложения Spring Boot с именем Backend Resources Application.
Он снабжен аннотацией @SpringBootApplication, которая представляет собой удобную аннотацию, включающую следующие примечания:

@Configuration: Указывает, что этот класс является классом Spring configuration.
@EnableAutoConfiguration: Включает функцию автоматической настройки Spring Boot, которая автоматически настраивает
компоненты в зависимости от наличия других компонентов и среды.
@ComponentScan: Сканирует текущий пакет и его подпакеты на наличие компонентов Spring (например, контроллеров, служб,
репозиториев).

Метод main() в этом классе является точкой входа в приложение Spring Boot. Он вызывает метод SpringApplication.run()
для запуска приложения.

Этот класс приложений служит основной отправной точкой для приложения Spring Boot, которое управляет внутренними
ресурсами, такими как пользователи и роли.
 */