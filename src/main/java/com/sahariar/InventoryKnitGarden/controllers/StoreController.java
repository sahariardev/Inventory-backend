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
import com.sahariar.InventoryKnitGarden.models.Store;
import com.sahariar.InventoryKnitGarden.requests.StoreRequest;
import com.sahariar.InventoryKnitGarden.services.StoreService;

@CrossOrigin
@RestController
@RequestMapping("api/stores")
public class StoreController {

	@Autowired
	StoreService storeService;

	@PostMapping()
	public ResponseEntity<String> createNewStore(@RequestBody StoreRequest request) {
		Long id = storeService.createNewStore(request);
		if (id != -1L) {
			return new ResponseEntity("created", HttpStatus.CREATED);
		}
		return new ResponseEntity("Bad Request", HttpStatus.BAD_REQUEST);
	}

	@GetMapping()
	public MappingJacksonValue getAllSotres() {
		List<Store> stores = storeService.getAllStores();
		SimpleBeanPropertyFilter storeFilter = SimpleBeanPropertyFilter.serializeAll();
		SimpleBeanPropertyFilter itemFilter = SimpleBeanPropertyFilter.serializeAllExcept("category");
		SimpleBeanPropertyFilter locationFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("ItemFilter", itemFilter)
				.addFilter("StoreFilter", storeFilter).addFilter("LocationFilter", locationFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(stores);
		mapping.setFilters(filters);
		return mapping;

	}

	@GetMapping("/{id}")
	public MappingJacksonValue getAllSotres(@PathVariable("id") Long id) {
		Store store = storeService.getOne(id);
		SimpleBeanPropertyFilter storeFilter = SimpleBeanPropertyFilter.serializeAllExcept("hibernateLazyInitializer","handler");
		SimpleBeanPropertyFilter itemFilter = SimpleBeanPropertyFilter.serializeAllExcept("category");
		SimpleBeanPropertyFilter locationFilter = SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters = new SimpleFilterProvider().addFilter("ItemFilter", itemFilter)
				.addFilter("StoreFilter", storeFilter).addFilter("LocationFilter", locationFilter);
		MappingJacksonValue mapping = new MappingJacksonValue(store);
		mapping.setFilters(filters);
		return mapping;

	}

}
