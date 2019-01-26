package com.sahariar.InventoryKnitGarden.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.services.UserService;

@CrossOrigin
@RestController
public class UserController {

	
	@Autowired
	UserService userService;
	
	@GetMapping("api/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	
	
	
}
