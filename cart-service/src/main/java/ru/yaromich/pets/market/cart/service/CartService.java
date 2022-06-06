package ru.yaromich.pets.market.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
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
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;

    public Cart getCurrentCart(String cartId) {
        if(!redisTemplate.hasKey(cartId)) {
            Cart cart = new Cart();
            redisTemplate.opsForValue().set(cartId, cart);
        }
        return (Cart) redisTemplate.opsForValue().get(cartId);
    }

    public void addToCart(String cartId, Long productId) {
        execute(cartId, cart -> {
            ProductDto p = productServiceIntegration.findById(productId);
            cart.add(p);
        });
    }

    public void removeFromCart(String cartId, Long productId) {
        execute(cartId, cart -> cart.remove(productId));
    }

    public void clearCart(String cartId) {
        execute(cartId, Cart::clear);
    }

    private void execute(String cartId, Consumer<Cart> action) {
        Cart cart = getCurrentCart(cartId);
        action.accept(cart);
        redisTemplate.opsForValue().set(cartId, cart);
    }


//    public Cart getMergeCart(String username, String guestCartId) {
//        if(!carts.containsKey(username)) {
//            Cart cart = new Cart();
//            carts.put(username, cart);
//        }
//        if(!carts.containsKey(guestCartId)) {
//            Cart cart = new Cart();
//            carts.put(guestCartId, cart);
//        }
//        Cart guestCart = carts.get(guestCartId);
//
//        Cart userCart = carts.get(username);
//
//        for (CartItem guestCartItem :  guestCart.getItems()) {
//            userCart.getItems().add(guestCartItem);
//        }
//        userCart.setTotalPrice(userCart.getTotalPrice().add(guestCart.getTotalPrice()));
//        guestCart.clear();
//        return userCart;
//    }
}
