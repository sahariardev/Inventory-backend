package com.sahariar.InventoryKnitGarden.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sahariar.InventoryKnitGarden.models.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	public List<Booking> findByCratedBy(String username);
	public List<Booking> findByStyleId(Long styleId);
	
	@Transactional
	@Modifying
	@Query("UPDATE Booking b SET b.status=:status where b.style.id=:id")
	public int updateBookingsStatusGruoupByStyle(@Param ("status") String status,@Param ("id") Long id);
	
	

}
