package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.exceptions.InventoryNotFoundException;
import com.fareye.ecommerce.managementv2.exceptions.ProductNotFoundException;
import com.fareye.ecommerce.managementv2.exceptions.UserNotFoundException;
import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InventoryService {

    Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    /**
     * Returns list of inventory for the given seller of username.
     * @param username of seller
     * @return list of inventory of seller
     */
    public List<Inventory> getInventoryForUsername(String username) {

        User user = getUserByUsername(username);

        Long userId = user.getId();

        return inventoryRepository.findInventoryByUserId(userId);
    }


    /**
     * Adds a existing product to seller's inventory.
     * @param username of seller.
     * @param productId of product to be added in inventory
     * @param inventory holds inventory details
     * @return saved inventory details
     */
    public Inventory addProductToInventory(String username,
                                           long productId,
                                           Inventory inventory) {

        User user = getUserByUsername(username);

        Product product = getProductById(productId);

        inventory.setUser(user);
        inventory.setProduct(product);

        return inventoryRepository.save(inventory);
    }

    /**
     * Update a product in inventory for a seller with username.
     * @param username of seller
     * @param inventoryId of inventory to be deleted
     * @param givenInventory stores updates parameters
     * @return new inventory details
     */
    public Inventory updateProductInInventory(String username,
                                              Long inventoryId,
                                              Inventory givenInventory) {

        Inventory inventory = getInventoryById(inventoryId);

        User user = getUserByUsername(username);

        if (user.getId() != inventory.getUser().getId()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        inventory.setQuantity(givenInventory.getQuantity());
        inventory.setPrice(givenInventory.getPrice());

        return inventoryRepository.save(inventory);
    }

    /**
     * Delete a product from sellers inventory.
     * @param username of seller
     * @param inventoryId of inventory to be deleted
     */
    public void deleteProductFromSellerInventory(String username,
                                                 Long inventoryId) {

        Inventory inventory = getInventoryById(inventoryId);

        User user = getUserByUsername(username);

        if (user.getId() != inventory.getUser().getId()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        inventoryRepository.delete(inventory);
    }


    /**
     * Get user by username.
     * @param username of the user.
     * @return user details.
     */
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found: username - " + username);
        }

        User user = optionalUser.get();

        return user;
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findProductById(productId);

        if (!optionalProduct.isPresent()) {
            throw new ProductNotFoundException("Product not found: id - " + productId);
        }

        Product product = optionalProduct.get();

        return product;
    }

    public Inventory getInventoryById(Long inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        if (!optionalInventory.isPresent()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        Inventory inventory = optionalInventory.get();

        return inventory;
    }
}
