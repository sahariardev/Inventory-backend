package com.sahariar.InventoryKnitGarden.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.Location;
import com.sahariar.InventoryKnitGarden.requests.LocationRequest;
import com.sahariar.InventoryKnitGarden.services.LocationService;

@CrossOrigin
@RestController
@RequestMapping("api/locations")
public class LocationController {
	@Autowired
	LocationService locationService;
	
	
	
	@GetMapping()
	public List<Location> getAll()
	{		
		return locationService.getAllLocation(); 
	}
	@PostMapping()
	public ResponseEntity<String> createOne(@RequestBody LocationRequest request)
	{
		long id=locationService.createNewLocation(request);
		if(id!=-1L)
		{
			return new ResponseEntity("Created",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity("Bad Request",HttpStatus.BAD_REQUEST);
		}
	}
}
