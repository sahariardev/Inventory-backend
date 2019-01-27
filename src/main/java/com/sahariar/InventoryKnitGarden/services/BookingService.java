package com.sahariar.InventoryKnitGarden.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.Booking;
import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.models.Style;
import com.sahariar.InventoryKnitGarden.models.Unit;
import com.sahariar.InventoryKnitGarden.repositories.BookingRepository;
import com.sahariar.InventoryKnitGarden.repositories.InventoryItemRepository;
import com.sahariar.InventoryKnitGarden.repositories.StyleRepository;
import com.sahariar.InventoryKnitGarden.repositories.UnitRepository;
import com.sahariar.InventoryKnitGarden.repositories.UserRepository;
import com.sahariar.InventoryKnitGarden.requests.BookingRequest;

@Service
public class BookingService {

	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	InventoryItemRepository itemRepository;
	
	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	StyleRepository styleRepository;
	
	
	
	public Long createNewBooking(BookingRequest request,String username) {
		
		Booking booking=new Booking();
		Unit unit=unitRepository.getOne(request.getUnit_id());
		InventoryItem item=itemRepository.getOne(request.getItem_id());
		Style style=styleRepository.getOne(request.getStyle_id());
		booking.setUnit(unit);
		booking.setItem(item);
		booking.setStyle(style);
		booking.setCratedBy(username);
		booking.setStatus("pending");
		booking.setQuantity(request.getQuantity());
		booking.setPriceperunit(request.getPriceperunit());
		booking.setDate(request.getDate());
		booking.setDescription(request.getDescription());
		try
		{
			bookingRepository.save(booking);
			return booking.getId();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return -1L;
		}		
		
	}
	
	public List<Booking> getAllBookingByLoggedInUser(String username)
	{
		return bookingRepository.findByCratedBy(username);
	}
	
}
