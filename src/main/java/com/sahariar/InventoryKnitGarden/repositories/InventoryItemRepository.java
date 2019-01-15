package com.sahariar.InventoryKnitGarden.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.InventoryItem;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

}
