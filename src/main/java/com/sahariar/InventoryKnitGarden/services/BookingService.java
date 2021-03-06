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
		booking.setCode("cyrrency:"+request.getCode());
		booking.setItem(item);
		booking.setExtra(request.getExtra());
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
	public List<Booking> getAllBookings()
	{
		return bookingRepository.findAll();
	}
	public List<Booking> getAllBookingsByStyle(Long styleId)
	
	{
		return bookingRepository.findByStyleId(styleId);
	}
	public boolean updateBookingsByStyleId(String status,Long id)
	{
		
		try
		{
			bookingRepository.updateBookingsStatusGruoupByStyle(status, id);	
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateFinalPrice(Long id,String price,String date)
	{
		String finalPriceWithDate=price+"#QQ#"+date;
		try
		{
			Booking booking=bookingRepository.getOne(id);
			
			String code=booking.getCode()+"#QQQ#"+"finalPrice:"+finalPriceWithDate;
			booking.setCode(code);
			bookingRepository.save(booking);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean changeStatus(Long item_id,String status,String username)
	{
		Booking booking=bookingRepository.getOne(item_id);
		booking.setStatus(status);
		booking.setUpdatedBy(username);
		
		try {
			bookingRepository.save(booking);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public Booking getOne(Long id)
	{
		return bookingRepository.getOne(id);
	}
	
}
