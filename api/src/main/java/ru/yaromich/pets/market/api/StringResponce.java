package ru.yaromich.pets.market.api;


public class StringResponce {
    private String value;

    public StringResponce(String value) {
        this.value = value;
    }

    public StringResponce() {}

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
