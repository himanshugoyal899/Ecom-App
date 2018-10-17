package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.exceptions.ProductNotFoundException;
import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import com.fareye.ecommerce.managementv2.services.utils.SellerForProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    /**
     * Get products by brand name.
     * @param brandName of product
     * @return list of products of brand brandName
     */
    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findAllByBrand(brandName);
    }


    /**
     * Get products by category.
     * @param categoryName of product
     * @return list of products of category categoryName.
     */
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findAllByCategory(categoryName);
    }

    /**
     * Get product details of product
     * @param productId of the product
     * @return the product with Id productId
     */
    public Product getProductDetails(Long productId) {
        Product product = getProductById(productId);
        return product;
    }

    /**
     * Get seller details for product
     * @param productId of product
     * @return list of sellers (with details)
     */
    public List<SellerForProduct> getSellersDetailsForProduct(Long productId) {
        List<SellerForProduct> list = new ArrayList<>();

        List<Inventory> inventoryList = inventoryRepository.findAllByProductId(productId);

        for (Inventory inventory: inventoryList) {
            Long sellerId = inventory.getUser().getId();
            User seller = userRepository.findById(sellerId).get();

            String sellerName = seller.getFirstName() + " " + seller.getLastName();
            SellerForProduct sellerForProduct = new SellerForProduct(sellerName, inventory.getQuantity(),
                    inventory.getPrice(), seller.getId());
            list.add(sellerForProduct);
        }

        return list;
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findProductById(productId);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product not found: id - " + productId);
        }

        Product product = optionalProduct.get();

        return product;
    }
}

