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
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public List<Order> findAllUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public Order findLastUserOrder(List<Order> orders) {

        List<LocalDateTime> localDateTimes = new ArrayList<>();

        for (Order order: orders) {
            localDateTimes.add(order.getCreatedAt());
        }

        LocalDateTime maxTime = localDateTimes.get(0);

        for (LocalDateTime localDateTime : localDateTimes) {
            if(localDateTime.compareTo(maxTime) > 0) {
                maxTime = localDateTime;
            }
        }
        return orderRepository.findByCreatedAt(maxTime);
    }

    @Transactional
    public void createOrderForUser(String userName) {
        CartDto c = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUsername(userName);
        order.setTotalPrice(c.getTotalPrice());

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItemDto cartItem : c.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPricePerProduct(cartItem.getPricePerProduct());
            Product product = productService.findById(cartItem.getProductId()).orElseThrow(() -> new ResourceNotFoundException("fdfdg"));
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }
        order.setItems(orderItems);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        cartServiceIntegration.clearCart();
    }
}





