package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

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
import com.sahariar.InventoryKnitGarden.models.Client;
import com.sahariar.InventoryKnitGarden.models.Role;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.requests.ClientRequest;
import com.sahariar.InventoryKnitGarden.services.ClientService;
import com.sahariar.InventoryKnitGarden.services.UserService;

@CrossOrigin
@RestController
@RequestMapping("api/clients")
public class ClientController {

	
	@Autowired
	UserService userService;
	 
	@Autowired
	ClientService clientService;
	
	
	@PostMapping()
	public ResponseEntity<String> cretaeNewClient(@RequestBody ClientRequest request){
		
		
		long id=clientService.createNewClient(request);
		
		if(id != -1L)
		{
			return new ResponseEntity("created", HttpStatus.CREATED);
			
		}
		else
		{
			return new ResponseEntity("bad request", HttpStatus.BAD_REQUEST);
		}		
	}
	@GetMapping()
	public MappingJacksonValue getAssiegnedClients(Principal principal)
	{
		String loggedInuserName=principal.getName();
		
		User user=userService.getOneUserByName(loggedInuserName);
	    
		boolean isGmOrMd=false;
		
		Set<Role> roles=user.getRoles();
		
		for(Role role:roles)
		{
			if(role.getRole().equals("managing_director") || role.getRole().equals("general_manager"))
			{
				isGmOrMd=true;
			}
		}
		List<Client> clients= null;
		if(isGmOrMd)
		{
			clients=clientService.getAllClients();
		}
		else
		{
			clients=clientService.getAllClientsByAssignedUserName(loggedInuserName);
		}
	
		SimpleBeanPropertyFilter filterClientFromProject=SimpleBeanPropertyFilter.serializeAllExcept("client");
		SimpleBeanPropertyFilter filterProjectFromStyle=SimpleBeanPropertyFilter.serializeAllExcept("project");
		SimpleBeanPropertyFilter mainFilter=SimpleBeanPropertyFilter.serializeAll();
		FilterProvider filters= new SimpleFilterProvider().addFilter("ProjectFilter", filterClientFromProject).addFilter("ClientFilter", mainFilter).addFilter("StyleFilter", filterProjectFromStyle);
		MappingJacksonValue mapping=new MappingJacksonValue(clients);
		mapping.setFilters(filters);
        return mapping;
		
		
		
	}
	
	
}
