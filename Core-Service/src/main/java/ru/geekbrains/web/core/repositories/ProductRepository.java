package ru.geekbrains.web.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.web.core.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByTitle(String title);

//    @Query("select p from Product p in p.product_comments WHERE p.id = :id")
    Optional<Product> findByIdWithComments(@Param("id") Long id/*, @Param("username") String username*/);


}
