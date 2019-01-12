package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@GetMapping("/hello")
	public String sayHello(Principal principal)
	{
		return "Hello "+principal.toString()+" name is "+principal.getName();
	}

}
