package ru.yaromich.pets.market.api;

public class StringRequest {
    private String value;

    public StringRequest(String value) {
        this.value = value;
    }

    public StringRequest() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

