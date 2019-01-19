package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class HelloController {

	
	@GetMapping("/hello")
	public String sayHello(Principal principal)
	{
		return "Hello "+principal.getName();
	}

}
