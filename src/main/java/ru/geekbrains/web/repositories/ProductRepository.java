package ru.geekbrains.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.web.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
