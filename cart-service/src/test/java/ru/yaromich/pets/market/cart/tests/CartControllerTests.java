package ru.yaromich.pets.market.cart.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.cart.integrations.ProductServiceIntegration;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductServiceIntegration productServiceIntegration;
//
//    @Test
//    public void getCurrentCartTest() throws Exception {
//        mockMvc
//                .perform(
//                        get("/api/v1/cart")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    public void addProductToCartTest() throws Exception {
//        ProductDto productDto = new ProductDto();
//        productDto.setPrice(BigDecimal.valueOf(100));
//        productDto.setTitle("Cat Chow");
//        productDto.setCategoryTitle("Эконом-класс");
//        productDto.setId(1L);
//        Mockito.doReturn(productDto)
//                .when(productServiceIntegration)
//                .findById(productDto.getId());
//
//        mockMvc.perform(get("/api/v1/cart/add/{productId}", 1L)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void clearCartTest() throws Exception {
//        mockMvc
//                .perform(
//                        get("/api/v1/cart/clear_cart")
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
