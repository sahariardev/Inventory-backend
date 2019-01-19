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
import com.sahariar.InventoryKnitGarden.models.Category;
import com.sahariar.InventoryKnitGarden.requests.CategoyRequest;
import com.sahariar.InventoryKnitGarden.services.CategoryService;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	CategoryService categoryService;
	
    @PostMapping() 
	public ResponseEntity<String> createNewCategory(@RequestBody CategoyRequest request,Principal principal)
	{
		
    	
    	String username=principal.getName();
    	
    	categoryService.createNewCategory(request.getName(), username);
    	return new ResponseEntity("Created",HttpStatus.CREATED);
	}
    @GetMapping()
    public MappingJacksonValue allCategories()
    {
    	
    	List<Category> categories=categoryService.getAllCategories();
		SimpleBeanPropertyFilter itemFilter=SimpleBeanPropertyFilter.serializeAllExcept("inventoryItems");
		FilterProvider filters= new SimpleFilterProvider().addFilter("CategoryFilter", itemFilter);
		MappingJacksonValue mapping=new MappingJacksonValue(categories);
		mapping.setFilters(filters);
        return mapping;
    	
    }
    
	
	
}
