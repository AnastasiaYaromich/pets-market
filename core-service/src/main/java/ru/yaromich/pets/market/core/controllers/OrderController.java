package ru.yaromich.pets.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.yaromich.pets.market.api.OrderDto;
import ru.yaromich.pets.market.core.converters.OrderConverter;

import ru.yaromich.pets.market.core.entities.Order;
import ru.yaromich.pets.market.core.services.OrderService;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @GetMapping()
    public OrderDto getLastUserOrder(Principal principal) {
        List<Order> orders = orderService.findAllUserOrders(principal.getName());
        return orderConverter.entitySingleToDto(orderService.findLastUserOrder(orders));
    }

    @PostMapping("/create")
    public void createNewOrder(Principal principal) {
       orderService.createOrderForUser(principal.getName());
    }
}

