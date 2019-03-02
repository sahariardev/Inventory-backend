package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sahariar.InventoryKnitGarden.models.Booking;
import com.sahariar.InventoryKnitGarden.models.Role;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.requests.BookingRequest;
import com.sahariar.InventoryKnitGarden.services.BookingService;
import com.sahariar.InventoryKnitGarden.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("api/booking")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@Autowired
	UserService userService;

	@PostMapping()
	public ResponseEntity<String> createNewBooking(@RequestBody BookingRequest request, Principal principal) {
		long id = bookingService.createNewBooking(request, principal.getName());

		if (id != -1L) {
			return new ResponseEntity("created", HttpStatus.CREATED);
		}
		return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
	}

	@GetMapping()
	public MappingJacksonValue allBookings() {

		List<Booking> bookings = bookingService.getAllBookings();
		SimpleBeanPropertyFilter bookingFilter = SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter itemFilter = SimpleBeanPropertyFilter.serializeAllExcept("category");
		SimpleBeanPropertyFilter styleFilter = SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter projectFilter = SimpleBeanPropertyFilter.serializeAllExcept("styles");
		SimpleBeanPropertyFilter clientFilter = SimpleBeanPropertyFilter.serializeAllExcept("projects");
		FilterProvider filters = new SimpleFilterProvider().addFilter("ItemFilter", itemFilter)
				.addFilter("BookingFilter", bookingFilter).addFilter("ProjectFilter", projectFilter)
				.addFilter("StyleFilter", styleFilter).addFilter("ClientFilter", clientFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(bookings);
		mapping.setFilters(filters);
		return mapping;

	}

	@GetMapping("/{booking_id}/changestatus/{status}")
	public ResponseEntity<String> changeBookingStatus(Principal principal, @PathVariable("booking_id") Long booking_id,
			@PathVariable("status") String status) {

		String username = principal.getName();
		boolean response = bookingService.changeStatus(booking_id, status, username);
		if (response) {
			return new ResponseEntity("Status changed", HttpStatus.OK);
		} else {
			return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/avalilablestatus/{booking_id}/")
	public List<String> statusOptions(Principal principal, @PathVariable("booking_id") Long booking_id) {
		String username = principal.getName();
		Booking booking = bookingService.getOne(booking_id);
		User user = userService.getOneUserByName(username);
		Set<Role> roleList = user.getRoles();
		List<String> status = new ArrayList();

		for (Role r : roleList) {
			String rolename = r.getRole();
			if (rolename.equals("general_manager") && !(booking.getStatus().equals("Processing")||(booking.getStatus().equals("In-housed")||booking.getStatus().equals("Pertially in housed")))) {
				status.add("Approved");
				status.add("Declined");
			} else if (rolename.equals("store_manager") && (booking.getStatus().equals("Approved")||booking.getStatus().equals("Processing")||(booking.getStatus().equals("In-housed")||booking.getStatus().equals("Pertially in housed")))) {
				status.add("Processing");
				status.add("In-housed");
				status.add("Pertially in-housed");
			}

		}

		return status;
	}

	@GetMapping("/{booking_id}")
	public MappingJacksonValue oneBooking(Principal principal, @PathVariable("booking_id") Long booking_id) {
		Booking booking = bookingService.getOne(booking_id);
		SimpleBeanPropertyFilter bookingFilter = SimpleBeanPropertyFilter.serializeAllExcept("hibernateLazyInitializer","handler");
		SimpleBeanPropertyFilter itemFilter = SimpleBeanPropertyFilter.serializeAllExcept("category");
		SimpleBeanPropertyFilter styleFilter = SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter projectFilter = SimpleBeanPropertyFilter.serializeAllExcept("styles");
		SimpleBeanPropertyFilter clientFilter = SimpleBeanPropertyFilter.serializeAllExcept("projects");
		FilterProvider filters = new SimpleFilterProvider().addFilter("ItemFilter", itemFilter)
				.addFilter("BookingFilter", bookingFilter).addFilter("ProjectFilter", projectFilter)
				.addFilter("StyleFilter", styleFilter).addFilter("ClientFilter", clientFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(booking);
		mapping.setFilters(filters);
		return mapping;
	}
}
