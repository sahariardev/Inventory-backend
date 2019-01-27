package com.sahariar.InventoryKnitGarden.controllers;

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
import com.sahariar.InventoryKnitGarden.models.Category;
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
    public MappingJacksonValue allCategories()
    {
    	
    	List<Location> locations=locationService.getAllLocation(); 
		SimpleBeanPropertyFilter itemFilter=SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters= new SimpleFilterProvider().addFilter("LocationFilter", itemFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(locations);
		mapping.setFilters(filters);
        return mapping;
    	
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
