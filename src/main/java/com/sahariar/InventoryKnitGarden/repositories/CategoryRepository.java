package com.sahariar.InventoryKnitGarden.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
