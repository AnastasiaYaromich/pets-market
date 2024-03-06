package ru.yaromich.pets.market.core.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import ru.yaromich.pets.market.core.entities.Category;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.Collections;

@JsonTest
public class JsonTests {
    @Autowired
    private JacksonTester<Category> jackson;

    @Test
    public void jsonSerializationTest() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Эконом класс");
        category.setProducts(Collections.emptyList());

        assertThat(jackson.write(category))
                .hasJsonPathNumberValue("$.id")
                .extractingJsonPathStringValue("$.title").isEqualTo("Эконом класс");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String content = "{\"id\": 2,\"title\":\"Эконом класс\",\"products\": []}";
        Category expectedCategory = new Category();
        expectedCategory.setId(2L);
        expectedCategory.setTitle("Эконом класс");
        expectedCategory.setProducts(Collections.emptyList());

        assertThat(jackson.parse(content)).isEqualTo(expectedCategory);
        assertThat(jackson.parseObject(content).getTitle()).isEqualTo("Эконом класс");
    }
}

