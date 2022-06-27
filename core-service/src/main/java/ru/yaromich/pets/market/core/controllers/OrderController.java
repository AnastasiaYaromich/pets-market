package ru.yaromich.pets.market.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.JwtRequest;
import ru.yaromich.pets.market.api.OrderDto;
import ru.yaromich.pets.market.api.OrderRequest;
import ru.yaromich.pets.market.core.converters.OrderConverter;
import ru.yaromich.pets.market.core.services.OrderService;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Заказы", description = "Методы работы с заказами")
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @Operation(
            summary = "Запрос на получение списка заказов пользователя",
            responses = {
                    @ApiResponse(
                            description = "Список заказов пользователя успешно найден", responseCode = "200"
                    )
            }

    )
    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }


    @Operation(
            summary = "Запрос на создание нового заказа",
            responses = {
                    @ApiResponse(
                            description = "Заказ успешно создан", responseCode = "201"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username, @RequestBody OrderRequest orderRequest) {
        orderService.createNewOrder(username, orderRequest.getAddress(), orderRequest.getPhone());
    }
}

