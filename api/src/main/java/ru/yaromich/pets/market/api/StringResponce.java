package ru.yaromich.pets.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Моель для строковых значений")
public class StringResponce {
    @Schema(description = "Ответ", required = true)
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
