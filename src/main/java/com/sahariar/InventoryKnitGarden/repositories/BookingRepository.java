package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sahariar.InventoryKnitGarden.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	public List<Booking> findByCratedBy(String username);
	public List<Booking> findByStyleId(Long styleId);
	
	@Transactional
	@Query("UPDATE Booking b SET e.status=:sts where e.style.id=:stl_id;")
	public void updateBookingsStatusGruoupByStyle(String sts,long stl_id);

}
