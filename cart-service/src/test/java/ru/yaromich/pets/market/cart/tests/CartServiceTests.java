package ru.yaromich.pets.market.cart.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.cart.integrations.ProductServiceIntegration;
import ru.yaromich.pets.market.cart.service.CartService;

import java.math.BigDecimal;

@SpringBootTest(classes = CartService.class)
public class CartServiceTests {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;


    @Test
    public void addToCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Cat Chow");
        productDto.setPrice(BigDecimal.valueOf(100));
        productDto.setCategoryTitle("Эконом класс");
        Mockito.doReturn(productDto)
                .when(productServiceIntegration)
                .findById(productDto.getId());
        cartService.addToCart(productDto.getId());
        Assertions.assertEquals(1, cartService.getCurrentCart().getItems().size());
    }
}