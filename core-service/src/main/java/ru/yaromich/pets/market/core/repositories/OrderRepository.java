package ru.yaromich.pets.market.core.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yaromich.pets.market.core.entities.Order;
//import ru.yaromich.pets.market.core.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
 //   Optional<Order> findByUserId(Long userId);
    List<Order> findAllByUsername(String username);
    Order findByCreatedAt(LocalDateTime localDateTime);

}

