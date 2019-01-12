package com.sahariar.InventoryKnitGarden.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRole(String role);

}
