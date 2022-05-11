package ru.yaromich.pets.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yaromich.pets.market.api.*;
import ru.yaromich.pets.market.core.entities.Order;
import ru.yaromich.pets.market.core.entities.OrderItem;
import ru.yaromich.pets.market.core.entities.Product;
import ru.yaromich.pets.market.core.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.core.integrations.CartServiceIntegration;
import ru.yaromich.pets.market.core.repositories.OrderItemRepository;
import ru.yaromich.pets.market.core.repositories.OrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public void createNewOrder(String username, String address, String phone) {
        CartDto cart = cartServiceIntegration.getCurrentUserCart(username);
        if(cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Нельзя оформить заказ для пустой корзины");
        }
        Order order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setUsername(username);
        order.setAddress(address);
        order.setPhone(phone);
        order.setItems(new ArrayList<>());
        cart.getItems().stream().forEach(ci -> {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setPrice(ci.getPrice());
            oi.setQuantity(ci.getQuantity());
            oi.setPricePerProduct(ci.getPricePerProduct());
            oi.setProduct(productService.findById(ci.getProductId()).orElseThrow(() ->
                    new ResourceNotFoundException("Product not found")));
            order.getItems().add(oi);
        });
        orderRepository.save(order);
        cartServiceIntegration.clearCart(username);
    }

    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}





