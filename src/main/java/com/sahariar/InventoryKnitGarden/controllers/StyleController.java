package com.sahariar.InventoryKnitGarden.controllers;

import java.util.List;

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
import com.sahariar.InventoryKnitGarden.models.Style;
import com.sahariar.InventoryKnitGarden.requests.StyleRequest;
import com.sahariar.InventoryKnitGarden.services.StyleService;

@CrossOrigin
@RestController
@RequestMapping("api/styles")
public class StyleController {

	
	@Autowired
	StyleService styleService;
	
	
	@PostMapping()
	public ResponseEntity<String> createNewStyle(@RequestBody StyleRequest request)
	{
		long id=styleService.createNewStyle(request);
		if(id !=-1L)
		  {
			  return new ResponseEntity("cretaed", HttpStatus.CREATED);
		  }
		  return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/project/{id}")
	public MappingJacksonValue gettAllStylesByProjectId(@PathVariable ("id") Long id)
	{
		List<Style> styles=styleService.getAllStyleByProjectId(id);
		SimpleBeanPropertyFilter styleFilter=SimpleBeanPropertyFilter.serializeAllExcept("style");
		SimpleBeanPropertyFilter clientFilter=SimpleBeanPropertyFilter.serializeAllExcept("client");
		FilterProvider filters=new SimpleFilterProvider().addFilter("ProjectFilter",styleFilter).addFilter("ClientFilter", clientFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(styles);
		mapping.setFilters(filters);
        return mapping;
	}
	
	
}
