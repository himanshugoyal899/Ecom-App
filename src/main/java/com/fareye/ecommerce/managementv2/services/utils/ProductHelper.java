package com.fareye.ecommerce.managementv2.services.utils;

import com.fareye.ecommerce.managementv2.exceptions.ProductNotFoundException;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductHelper {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findProductById(productId);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product not found: id - " + productId);
        }

        Product product = optionalProduct.get();

        return product;
    }
}
