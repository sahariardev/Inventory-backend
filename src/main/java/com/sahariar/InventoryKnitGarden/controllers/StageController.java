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

import com.sahariar.InventoryKnitGarden.models.Stage;
import com.sahariar.InventoryKnitGarden.requests.StageRequest;
import com.sahariar.InventoryKnitGarden.services.StageService;

@CrossOrigin
@RestController
@RequestMapping("api/stages")
public class StageController {

	
	@Autowired
	StageService stageService;
	
	@PostMapping()
	public ResponseEntity<String> createNewStage(@RequestBody StageRequest request)
	{
		long id =stageService.createNewStage(request);
		if(id != -1L)
		{
			return new ResponseEntity("created",HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity("Bad Request",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping()
	public List<Stage> getAllStages()
	{
		return stageService.getAllStage();
	}
	
	
}
