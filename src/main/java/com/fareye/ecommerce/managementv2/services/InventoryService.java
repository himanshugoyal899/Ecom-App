package com.fareye.ecommerce.managementv2.services;

import com.fareye.ecommerce.managementv2.exceptions.InventoryNotFoundException;
import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.models.Product;
import com.fareye.ecommerce.managementv2.models.User;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import com.fareye.ecommerce.managementv2.repositories.ProductRepository;
import com.fareye.ecommerce.managementv2.repositories.UserRepository;
import com.fareye.ecommerce.managementv2.services.utils.InventoryHelper;
import com.fareye.ecommerce.managementv2.services.utils.ProductHelper;
import com.fareye.ecommerce.managementv2.services.utils.UserHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;


    private UserHelper userHelper;

    public InventoryService() {
        this.userHelper = new UserHelper(userRepository);
    }

    /**
     * Returns list of inventory for the given seller of username.
     * @param username
     * @return Optional
     */
    public List<Inventory> getInventoryForUsername(String username) {

        User user = userHelper.getUserByUsername(username);

        Long userId = user.getId();
        return inventoryRepository.findInventoryByUserId(userId);
    }


    /**
     * Adds a existing product to seller's inventory.
     * @param username
     * @param productId
     * @param inventory
     * @return
     */
    public Inventory addProductToInventory(String username,
                                           long productId,
                                           Inventory inventory) {

        User user = userHelper.getUserByUsername(username);

        Product product = ProductHelper.getProductById(productId);

        inventory.setUser(user);
        inventory.setProduct(product);

        return inventoryRepository.save(inventory);
    }

    /**
     * Update a product in inventory for a seller with username.
     * @param username
     * @param inventoryId
     * @param givenInventory
     * @return
     */
    public Inventory updateProductInInventory(String username,
                                              Long inventoryId,
                                              Inventory givenInventory) {

        Inventory inventory = InventoryHelper.getInventoryById(inventoryId);

        User user = userHelper.getUserByUsername(username);

        if (user.getId() != inventory.getUser().getId()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        inventory.setQuantity(givenInventory.getQuantity());
        inventory.setPrice(givenInventory.getPrice());

        return inventoryRepository.save(inventory);
    }

    /**
     * Delete a product from sellers inventory.
     * @param username
     * @param inventoryId
     */
    public void deleteProductFromSellerInventory(String username,
                                                 Long inventoryId) {

        Inventory inventory = InventoryHelper.getInventoryById(inventoryId);

        User user = userHelper.getUserByUsername(username);

        if (user.getId() != inventory.getUser().getId()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        inventoryRepository.delete(inventory);
    }
}
