package ru.yaromich.pets.market.core.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.yaromich.pets.market.api.ProductDto;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class FullServerRunTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void fullRestTest() {
        List<ProductDto> products = restTemplate.getForObject("/api/v1/products", List.class);
        assertThat(products)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5);
    }
}
