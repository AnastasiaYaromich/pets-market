package ru.yaromich.pets.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.yaromich.pets.market.api.OrderDto;
import ru.yaromich.pets.market.core.entities.Order;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        return new OrderDto(o.getId(), o.getTotalPrice(), o.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors
        .toList()));
    }
}
