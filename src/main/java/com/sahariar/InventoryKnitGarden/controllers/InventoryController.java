package com.sahariar.InventoryKnitGarden.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.requests.InventoryItemRequest;
import com.sahariar.InventoryKnitGarden.services.InventoryItemService;

@RestController
@RequestMapping("/api/items")
public class InventoryController {

	@Autowired
	InventoryItemService inventroyService;
	
	
	@PostMapping()
	public ResponseEntity<String> createNewInventoryItem(@RequestBody InventoryItemRequest request)
	{
		long id=inventroyService.createNewInventoryItem(request);
		if(id!=-1L) return new ResponseEntity<String>("Created",HttpStatus.CREATED);
		return new ResponseEntity<String>("Bad Request",HttpStatus.BAD_REQUEST);	
	}
	
	
	
}
