package ru.geekbrains.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.web.model.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
