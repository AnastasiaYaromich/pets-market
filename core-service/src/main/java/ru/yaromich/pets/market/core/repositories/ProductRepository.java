package ru.yaromich.pets.market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yaromich.pets.market.core.entities.Category;
import ru.yaromich.pets.market.core.entities.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findOneById(Long id);
    Optional<Product> findByTitle(String title);
}
