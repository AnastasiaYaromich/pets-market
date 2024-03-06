package ru.yaromich.pets.market.cart.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.CartDto;
import ru.yaromich.pets.market.api.StringResponce;
import ru.yaromich.pets.market.cart.converters.CartConverter;
import ru.yaromich.pets.market.cart.service.CartService;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Tag(name = "Корзина", description = "Методы работы с корзиной")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;


    @Operation(
            summary = "Запрос на создание ID корзины неавторизованного пользователя",
            responses = {
                    @ApiResponse(
                            description = "ID корзины неавторизованного пользователя успешно создан", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = StringResponce.class))
                    )
            }
    )
    @GetMapping("/generate_id")
    @CrossOrigin
    public StringResponce generateGuestCartId() {
        return new StringResponce(UUID.randomUUID().toString());
    }


    @Operation(
            summary = "Запрос на получение корзины",
            responses = {
                    @ApiResponse(
                            description = "Корзина успешно найдена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = CartDto.class))
                    )
            }
    )
    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }


    @Operation(
            summary = "Запрос на добавление продукта в корзину",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно добавлен в корзину", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @Operation(
            summary = "Запрос на очистку корзины",
            responses = {
                    @ApiResponse(
                            description = "Корзина успешно очищена", responseCode = "200"
                    )
            }
    )
    @GetMapping("/{guestCartId}/clear")
    public void clearCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCart(currentCartId);
    }

    private String selectCartId(String username, String guestCartId) {
        if(username != null) {
            return username;
        }
        return guestCartId;
    }
}