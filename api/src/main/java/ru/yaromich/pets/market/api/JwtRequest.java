package ru.yaromich.pets.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель логина/пароля для авторизации")
public class JwtRequest {
    @Schema(description = "Логин", required = true, example = "bob")
    private String username;

    @Schema(description = "Пароль", required = true, example = "100")
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}