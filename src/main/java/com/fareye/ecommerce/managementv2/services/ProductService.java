package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import com.fareye.ecommerce.managementv2.services.utils.ProductHelper;
import com.fareye.ecommerce.managementv2.services.utils.SellerForProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        Product product = ProductHelper.getProductById(productId);
        productRepository.delete(product);
    }

    /*public List<String> getAllBrandNames() {
        List<String> list = productRepository.findDistinctBrand();
        return list;
    }*/

    public List<Product> getProductsByBrand(String brandName) {
        return productRepository.findAllByBrand(brandName);
    }

    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findAllByCategory(categoryName);
    }

    public Product getProductDetails(Long productId) {
        Product product = ProductHelper.getProductById(productId);
        return product;
    }

    /**
     * TO BE TESTED.
     * @param productId
     * @return
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
}

