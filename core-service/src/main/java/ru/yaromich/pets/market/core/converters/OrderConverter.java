package ru.yaromich.pets.market.core.converters;

import org.springframework.stereotype.Component;
import ru.yaromich.pets.market.api.OrderDto;
import ru.yaromich.pets.market.api.OrderItemDto;
import ru.yaromich.pets.market.core.entities.Order;
import ru.yaromich.pets.market.core.entities.OrderItem;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {


    public List<OrderDto> entityToDto(List<Order> orders) {
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order o : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(o.getId());
            orderDto.setTotalPrice(o.getTotalPrice());

            List<OrderItem> orderItemList = o.getItems();
            orderDto.setItems(orderItemToDtoList(orderItemList));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    public OrderDto entitySingleToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setId(order.getId());
        List<OrderItemDto> list = new ArrayList<>();

        for (OrderItem orderItem: order.getItems()) {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDto.setPrice(orderItem.getPrice());
            orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
            orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
            list.add(orderItemDto);
        }
        orderDto.setItems(list);
        return orderDto;
    }


    public List<OrderItemDto> orderItemToDtoList(List<OrderItem> orderItems) {

        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        for (OrderItem orderItem: orderItems) {
            OrderItemDto orderItemDto = new OrderItemDto();

            orderItemDto.setPrice(orderItem.getPrice());
            orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
            orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDtoList.add(orderItemDto);
        }
        return orderItemDtoList;
    }

}
