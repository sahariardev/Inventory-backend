package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	public List<Project> findByClientId(Long id);
	public List<Project> findByClientAssignedId(Long id);

}
