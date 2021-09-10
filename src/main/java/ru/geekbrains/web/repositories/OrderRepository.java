package ru.geekbrains.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.web.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
