package com.fareye.ecommerce.managementv2.repositories;

import com.fareye.ecommerce.managementv2.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findInventoryByUserId(long userId);
    Optional<Inventory> findById(long id);
    List<Inventory> findAllByProductId(Long productId);
}
