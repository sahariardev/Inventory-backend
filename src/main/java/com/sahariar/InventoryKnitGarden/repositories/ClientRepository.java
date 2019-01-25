package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Client;



public interface ClientRepository extends JpaRepository<Client, Long> {

	public List<Client> findClientByAssignedUsername(String username);
	
}
