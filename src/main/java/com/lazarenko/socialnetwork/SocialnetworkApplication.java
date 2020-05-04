package com.lazarenko.socialnetwork;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Заготовка для социальной сети
 * Цель: В результате выполнения ДЗ вы создадите базовый скелет социальной сети, который будет развиваться в дальнейших ДЗ.
 * В данном задании тренируются навыки: - декомпозиции предметной области; - построения элементарной архитектуры проекта
 * Требуется разработать создание и просмотр анект в социальной сети.
 *
 * Функциональные требования:
 * - Авторизация по паролю.
 * - Страница регистрации, где указывается следующая информация:
 * 1) Имя
 * 2) Фамилия
 * 3) Возраст
 * 4) Пол
 * 5) Интересы
 * 6) Город
 * - Страницы с анкетой.
 *
 * Нефункциональные требования:
 * - Любой язык программирования
 * - В качестве базы данных использовать MySQL
 * - Не использовать ORM
 * - Программа должна представлять из себя монолитное приложение.
 * - Не рекомендуется использовать следующие технологии:
 * 1) Репликация
 * 2) Шардинг
 * 3) Индексы
 * 4) Кэширование
 *
 *
 * Верстка не важна. Подойдет самая примитивная.
 *
 * Разместить приложение на любом хостинге. Например, heroku.
 *
 * ДЗ принимается в виде исходного кода на github и демонстрации проекта на хостинге.
 * Критерии оценки: Оценка происходит по принципу зачет/незачет.
 *
 * Требования:
 * Есть возможность регистрации, создавать персональные страницы, возможность подружиться, список друзей.
 * Отсутствуют SQL-инъекции.
 * Пароль хранится безопасно.
 */
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
public class SocialnetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialnetworkApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}