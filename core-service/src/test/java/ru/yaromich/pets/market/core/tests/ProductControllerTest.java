package ru.yaromich.pets.market.core.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.yaromich.pets.market.api.ProductDto;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;
import java.io.IOException;
import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerTest {
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    public void getAllProductsTest() throws Exception {
//        mvc
//                .perform(
//                        get("/api/v1/products")
//                              .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$").isArray())
//                .andExpect(jsonPath("$", hasSize(5)))
//                .andExpect(jsonPath("$[2].title", is("Royal Canin")));
//    }
//
//    @Test
//    public void createProductTest() throws Exception {
//        ProductDto productDto = new ProductDto(null, "Demo", BigDecimal.valueOf(100.00), "Холистик");
//        mvc
//                .perform(
//                        post("/api/v1/products")
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .content(new ObjectMapper().writeValueAsString(productDto))
//                                .header("username", "Bob") // Здесь не особо нужно
//                )
//                .andDo(print())
//                .andExpect(status().isCreated());
//    }
}
