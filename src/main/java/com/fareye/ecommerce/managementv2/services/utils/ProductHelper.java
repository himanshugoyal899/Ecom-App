package com.fareye.ecommerce.managementv2.services.utils;

import com.fareye.ecommerce.managementv2.exceptions.ProductNotFoundException;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProductHelper {
    @Autowired
    private static ProductRepository productRepository;

    public static Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findProductById(productId);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product not found: id - " + productId);
        }

        Product product = optionalProduct.get();

        return product;
    }
}
