package com.fareye.ecommerce.managementv2.repositories;

import com.fareye.ecommerce.managementv2.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CrudRepository<Product, Long> {
    Optional<Product> findProductById(long id);

    List<Product> findAllByBrand(String brand);

    List<Product> findAllByCategory(String category);

    /*@Query("SELECT DISTINCT brand FROM products")
    List<String> findDistinctBrand();*/
}
