package ru.yaromich.pets.market.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.yaromich.pets.market.api.CartDto;
import ru.yaromich.pets.market.core.integrations.CartServiceIntegration;
import ru.yaromich.pets.market.core.services.OrderService;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private OrderService orderService;

//    @Test
//    public void createNewOrderTest() throws Exception {
//        CartDto cartDto = new CartDto();
//        Mockito.doReturn(cartDto)
//                .when(cartServiceIntegration)
//                .getCurrentUserCart();
//        Mockito.doNothing()
//                .when(orderService)
//                .createNewOrder("bob");
//
//        mvc
//                .perform(
//                        post("/api/v1/orders/create")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .header("username", "Bob")
//                )
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}