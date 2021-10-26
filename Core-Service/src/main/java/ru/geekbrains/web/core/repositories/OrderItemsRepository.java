package ru.geekbrains.web.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.web.core.model.OrderItem;

public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
}
