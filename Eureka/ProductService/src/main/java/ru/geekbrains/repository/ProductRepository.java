package ru.geekbrains.repository;

import lombok.Data;
import ru.geekbrains.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.common.ProductDTO;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<ProductDTO> findAllBy();
}
