package ru.yaromich.pets.market.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.cart.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.cart.integrations.ProductServiceIntegration;
import ru.yaromich.pets.market.cart.utils.Cart;
import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCart(Long productId) {
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void clearCart() {
        getCurrentCart().getItems().clear();
        getCurrentCart().setTotalPrice(null);
    }

//    public void deleteFromCart(Long productId) {
//        Product p = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + productId + " не найден"));
//        cart.delete(p);
//    }
}
