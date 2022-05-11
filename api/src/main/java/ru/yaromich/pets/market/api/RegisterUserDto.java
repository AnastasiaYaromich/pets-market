package ru.yaromich.pets.market.api;

public class RegisterUserDto {
    private String username;
    private String password;
    private String confirmPassword;
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
