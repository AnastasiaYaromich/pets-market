package ru.yaromich.pets.market.core.converters;

import org.springframework.stereotype.Component;
import ru.yaromich.pets.market.api.OrderItemDto;
import ru.yaromich.pets.market.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
