package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Style;

public interface StyleRepository extends JpaRepository<Style, Long> {
	
	public List<Style> findByProjectId(Long id);

}
