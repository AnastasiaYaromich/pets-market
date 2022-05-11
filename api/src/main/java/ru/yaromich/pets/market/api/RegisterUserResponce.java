package ru.yaromich.pets.market.api;

public class RegisterUserResponce {
    private String email;
    private String username;

    public RegisterUserResponce(String username, String email) {
        this.email = email;
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
