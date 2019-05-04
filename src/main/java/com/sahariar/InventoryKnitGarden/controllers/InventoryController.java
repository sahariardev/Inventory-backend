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
import com.sahariar.InventoryKnitGarden.models.InventoryItem;
import com.sahariar.InventoryKnitGarden.requests.InventoryItemRequest;
import com.sahariar.InventoryKnitGarden.services.InventoryItemService;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class InventoryController {

	@Autowired
	InventoryItemService inventroyService;
	
	
	@PostMapping
	public ResponseEntity<String> createNewInventoryItem(@RequestBody InventoryItemRequest request,Principal pricipal)
	{
		
		String username=pricipal.getName();
		long id=inventroyService.createNewInventoryItem(request,username);
		if(id!=-1L) return new ResponseEntity<String>(id+"",HttpStatus.CREATED);
		return new ResponseEntity<String>("Bad Request",HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping
	public MappingJacksonValue getAllInventoryItems()
	{
		List<InventoryItem> items=inventroyService.getAll();
		SimpleBeanPropertyFilter itemFilter=SimpleBeanPropertyFilter.serializeAllExcept("inventoryItems");
		SimpleBeanPropertyFilter allSerializer=SimpleBeanPropertyFilter.serializeAll();
		
		FilterProvider filters= new SimpleFilterProvider().addFilter("CategoryFilter", itemFilter).addFilter("ItemFilter",allSerializer);
		MappingJacksonValue mapping=new MappingJacksonValue(items);
		mapping.setFilters(filters);
        return mapping;
		
	}
	
	
	
}
