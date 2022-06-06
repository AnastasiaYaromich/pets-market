package ru.yaromich.pets.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetsMarketCoreApplication {

    // Домашнее задание
    // 1. Разобраться с кодом. +++
    // 2. Замените Page, который сейчас используется на PageDto (подумайте, что в нем может быть). ++
    // 3. Исследовательская задача : Безопасность на gateway (либо реализация, либо предложения).
    // Например, пускать в cartService только авторизованных пользователей.

    public static void main(String[] args) {
        SpringApplication.run(PetsMarketCoreApplication.class, args);
    }
}
