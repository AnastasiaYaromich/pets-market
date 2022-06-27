package ru.yaromich.pets.market.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Модель заказа")
public class OrderDto {
    @Schema(description = "ID заказа", required = true, example = "1")
    private Long id;

    @Schema(description = "Общая стоимость заказа", required = true, example = "2400.0")
    private BigDecimal totalPrice;

    @Schema(description = "Список позиций в заказе", required = true)
    private List<OrderItemDto> items;

    public OrderDto(Long id, BigDecimal totalPrice, List<OrderItemDto> items) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public OrderDto() {
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
