package ru.yaromich.pets.market.cart.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.CartDto;
import ru.yaromich.pets.market.api.StringResponce;
import ru.yaromich.pets.market.cart.converters.CartConverter;
import ru.yaromich.pets.market.cart.service.CartService;
import ru.yaromich.pets.market.cart.utils.Cart;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponce generateGuestCartId() {
        return new StringResponce(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }

    @GetMapping("/{guestCartId}/add/{productId}")
    public void addProductToCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId, @PathVariable Long productId) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addToCart(currentCartId, productId);
    }

    @GetMapping("/{guestCartId}/merge_cart")
    public CartDto createMergeCart(@RequestHeader(required = false) String username, @PathVariable String guestCartId) {
        return  cartConverter.entityToDto(cartService.getMergeCart(username, guestCartId));
    }

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