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

import com.sahariar.InventoryKnitGarden.models.Unit;
import com.sahariar.InventoryKnitGarden.requests.UnitRequest;
import com.sahariar.InventoryKnitGarden.services.UnitService;

@CrossOrigin
@RestController
@RequestMapping("api/units")
public class UnitController {

	 @Autowired
	 UnitService unitService;
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody UnitRequest request)
	{
		Long id=unitService.createNewUnit(request);
		if(id!=-1L)
		{
			return new ResponseEntity<>("created",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
		}
				
		
	}
	@GetMapping
	public List<Unit> getAllUnits()
	{
		return unitService.getAllUnits();
	}
	
	
}
