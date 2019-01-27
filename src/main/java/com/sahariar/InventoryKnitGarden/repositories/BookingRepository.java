package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahariar.InventoryKnitGarden.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	public List<Booking> findByCratedBy(String username);

}
