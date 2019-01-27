package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sahariar.InventoryKnitGarden.models.Booking;
import com.sahariar.InventoryKnitGarden.requests.BookingRequest;
import com.sahariar.InventoryKnitGarden.services.BookingService;

@CrossOrigin
@RestController
@RequestMapping("api/booking")
public class BookingController {

	
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping()
	public ResponseEntity<String> createNewBooking(@RequestBody BookingRequest request,Principal principal)
	{
		long id = bookingService.createNewBooking(request, principal.getName());
		
		if(id!=-1L)
		{
			return new ResponseEntity("created",HttpStatus.CREATED);
		}
		return new ResponseEntity("Bad Request",HttpStatus.BAD_REQUEST);
	}
	
	 @GetMapping()
	 public MappingJacksonValue allCategories(Principal principal)
	 {
	    	
	    	List<Booking> bookings=bookingService.getAllBookingByLoggedInUser(principal.getName());
			SimpleBeanPropertyFilter bookingFilter=SimpleBeanPropertyFilter.serializeAll();
			SimpleBeanPropertyFilter itemFilter=SimpleBeanPropertyFilter.serializeAllExcept("category");
			SimpleBeanPropertyFilter styleFilter=SimpleBeanPropertyFilter.serializeAll();
			SimpleBeanPropertyFilter projectFilter=SimpleBeanPropertyFilter.serializeAllExcept("styles");
			SimpleBeanPropertyFilter clientFilter=SimpleBeanPropertyFilter.serializeAllExcept("projects");
			FilterProvider filters= new SimpleFilterProvider().addFilter("ItemFilter", itemFilter).addFilter("BookingFilter", bookingFilter)
					.addFilter("ProjectFilter",projectFilter)
					.addFilter("StyleFilter", styleFilter)
					.addFilter("ClientFilter", clientFilter);
			MappingJacksonValue mapping=new MappingJacksonValue(bookings);
			mapping.setFilters(filters);
	        return mapping;
	    	
	 }
	
	
}
