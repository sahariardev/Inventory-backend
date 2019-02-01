package com.sahariar.InventoryKnitGarden.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

}
