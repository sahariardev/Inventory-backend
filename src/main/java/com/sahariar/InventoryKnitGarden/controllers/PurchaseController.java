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
import com.sahariar.InventoryKnitGarden.models.Purchase;
import com.sahariar.InventoryKnitGarden.requests.PurchaseRequest;
import com.sahariar.InventoryKnitGarden.services.PurchaseService;

@CrossOrigin
@RestController
@RequestMapping("api/purchases")
public class PurchaseController {

	
	@Autowired
	PurchaseService purchaseService;
	
	
	@PostMapping()
	public ResponseEntity<String> createNewPurchase(@RequestBody PurchaseRequest request)
	{
		Long id=purchaseService.createNewPurchase(request);
		
		if(id != -1)
		{
			return new ResponseEntity("created",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity("Bad Request",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping()
	public MappingJacksonValue getAllSotres()
	{
		List<Purchase> purchases=purchaseService.getAllPurchases();
		SimpleBeanPropertyFilter purchaseFilter=SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter itemFilter=SimpleBeanPropertyFilter.serializeAllExcept("category");
		FilterProvider filters= new SimpleFilterProvider().addFilter("ItemFilter", itemFilter).addFilter("PurchaseFilter", purchaseFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(purchases);
		mapping.setFilters(filters);
        return mapping;
		
	}
	
	
	
	
	
	
}
