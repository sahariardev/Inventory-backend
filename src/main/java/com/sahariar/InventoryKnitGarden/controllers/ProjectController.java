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
import com.sahariar.InventoryKnitGarden.models.Project;
import com.sahariar.InventoryKnitGarden.requests.ProjectRequest;
import com.sahariar.InventoryKnitGarden.services.ProjectService;

@CrossOrigin
@RestController
@RequestMapping("api/projects")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	
	@PostMapping()
	public ResponseEntity<String> createNewProject(@RequestBody ProjectRequest request)
	{
		
	  long id=projectService.createNewProject(request);
	  
	  if(id !=-1L)
	  {
		  return new ResponseEntity("cretaed", HttpStatus.CREATED);
	  }
	  return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
		
	}
	
	@GetMapping("/client/{id}")
	public MappingJacksonValue getProjectsByClientId(@PathVariable ("id") Long id)
	{
		List<Project> projects= projectService.getProjectByClientId(id);
		SimpleBeanPropertyFilter projectFilter=SimpleBeanPropertyFilter.serializeAllExcept("project");
		FilterProvider filter=new SimpleFilterProvider().addFilter("StyleFilter",projectFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(projects);
		mapping.setFilters(filter);
        return mapping;
				
	}
	
}