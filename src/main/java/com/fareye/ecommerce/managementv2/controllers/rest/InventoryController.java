package com.fareye.ecommerce.managementv2.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;

import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(path = "api/seller/{username}/inventory/products")
    public List<Inventory> getInventoryForUsername(@PathVariable String username) {
        return inventoryService.getInventoryForUsername(username);
    }

    @PostMapping(path = "api/seller/{username}/inventory/{productId}")
    public Inventory addProductToInventory(@PathVariable String username,
                                           @PathVariable Long productId,
                                           @RequestBody Inventory inventory) {
        return inventoryService.addProductToInventory(username, productId, inventory);
    }

    @PutMapping(path = "api/seller/{username}/inventory/{inventoryId}")
    public Inventory updateProductInInventory(@PathVariable String username,
                                              @PathVariable Long inventoryId,
                                              @RequestBody Inventory givenInventory) {
        return inventoryService.updateProductInInventory(username,
                inventoryId,
                givenInventory);
    }

    @DeleteMapping(path = "api/seller/{username}/inventory/{inventoryId}")
    public void deleteProductFromSellerInventory(@PathVariable String username,
                                                 @PathVariable Long inventoryId) {
        inventoryService.deleteProductFromSellerInventory(username, inventoryId);
    }
}
