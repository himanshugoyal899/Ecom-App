package com.fareye.ecommerce.managementv2.services.utils;

import com.fareye.ecommerce.managementv2.exceptions.InventoryNotFoundException;
import com.fareye.ecommerce.managementv2.models.Inventory;
import com.fareye.ecommerce.managementv2.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class InventoryHelper {

    @Autowired
    private static InventoryRepository inventoryRepository;

    public static Inventory getInventoryById(Long inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        if (!optionalInventory.isPresent()) {
            throw new InventoryNotFoundException("Inventory not found: id - " + inventoryId);
        }

        Inventory inventory = optionalInventory.get();

        return inventory;
    }
}
