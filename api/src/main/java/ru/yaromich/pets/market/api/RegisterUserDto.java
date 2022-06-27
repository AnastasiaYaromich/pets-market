package ru.yaromich.pets.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Модель для регистрации")
public class RegisterUserDto {
    @Schema(description = "Логин", required = true, example = "bob")
    private String username;

    @Schema(description = "Пароль", required = true, example = "100")
    private String password;

    @Schema(description = "Подтверждение пароля", required = true, example = "100")
    private String confirmPassword;

    @Schema(description = "Email", required = true, example = "bob@mail.ru")
    private String email;

    public RegisterUserDto(String username, String password, String email) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public RegisterUserDto() {}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
