package ru.yaromich.pets.market.api;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {
    private Long id;
    private BigDecimal totalPrice;
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
