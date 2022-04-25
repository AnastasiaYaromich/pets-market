package ru.yaromich.pets.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.OrderDto;
import ru.yaromich.pets.market.core.converters.OrderConverter;
import ru.yaromich.pets.market.core.entities.Order;
import ru.yaromich.pets.market.core.services.OrderService;
import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping()
    public OrderDto getLastUserOrder(@RequestHeader String username) {
        List<Order> orders = orderService.findAllUserOrders(username);
        return orderConverter.entitySingleToDto(orderService.findLastUserOrder(orders));
    }

    @PostMapping("/create")
    public void createNewOrder(@RequestHeader String username) {
        System.out.println(username);
       orderService.createOrderForUser(username);
    }
}

