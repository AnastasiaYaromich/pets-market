package ru.yaromich.pets.market.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.cart.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.cart.integrations.ProductServiceIntegration;
import ru.yaromich.pets.market.cart.utils.Cart;
import ru.yaromich.pets.market.cart.utils.CartItem;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Map<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String cartId) {
        if(!carts.containsKey(cartId)) {
            Cart cart = new Cart();
            carts.put(cartId, cart);
        }
        return carts.get(cartId);
    }

    public void addToCart(String cartId, Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        getCurrentCart(cartId).add(p);
    }

    public void clearCart(String cartId) {
        getCurrentCart(cartId).clear();
    }


    public Cart getMergeCart(String username, String guestCartId) {
        if(!carts.containsKey(username)) {
            Cart cart = new Cart();
            carts.put(username, cart);
        }
        if(!carts.containsKey(guestCartId)) {
            Cart cart = new Cart();
            carts.put(guestCartId, cart);
        }
        Cart guestCart = carts.get(guestCartId);

        Cart userCart = carts.get(username);

        for (CartItem guestCartItem :  guestCart.getItems()) {
            userCart.getItems().add(guestCartItem);
        }
        userCart.setTotalPrice(userCart.getTotalPrice().add(guestCart.getTotalPrice()));
        guestCart.clear();
        return userCart;
    }
}
