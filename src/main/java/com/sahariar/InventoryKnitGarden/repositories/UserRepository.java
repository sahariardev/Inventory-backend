package com.sahariar.InventoryKnitGarden.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.User;


public interface UserRepository extends JpaRepository<User,Long> {

	public User findUserByUsername(String name);
	
	
}
