package ru.yaromich.pets.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.CartDto;
import ru.yaromich.pets.market.cart.converters.CartConverter;
import ru.yaromich.pets.market.cart.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.addToCart(id);
    }

    @GetMapping("/clear_cart")
    public void deleteAllItemsInCart() {
        cartService.clearCart();
    }
}