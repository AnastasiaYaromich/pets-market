package ru.yaromich.pets.market.core.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.yaromich.pets.market.core.entities.Category;
import ru.yaromich.pets.market.core.repositories.CategoryRepository;

import java.util.Collections;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CategoryRepositoryTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findAllTest() {
        Category category  = new Category();
        category.setTitle("Игрушки");
        category.setProducts(Collections.emptyList());
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoryList = categoryRepository.findAll();
        Assertions.assertEquals(6, categoryList.size());
        Assertions.assertEquals("Эконом класс", categoryList.get(0).getTitle());
    }
}
