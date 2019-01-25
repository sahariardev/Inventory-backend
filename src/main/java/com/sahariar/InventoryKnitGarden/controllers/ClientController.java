package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
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

import com.sahariar.InventoryKnitGarden.models.Client;
import com.sahariar.InventoryKnitGarden.requests.ClientRequest;
import com.sahariar.InventoryKnitGarden.services.ClientService;

@CrossOrigin
@RestController
@RequestMapping("api/clients")
public class ClientController {

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
	public List<Client> getAssiegnedClients(Principal principal)
	{
		String loggedInuserName=principal.getName();
		return clientService.getAllClientsByAssignedUserName(loggedInuserName);
	}
	
	
}
