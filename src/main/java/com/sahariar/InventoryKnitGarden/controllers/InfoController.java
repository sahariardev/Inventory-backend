package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.Role;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("api/info")
public class InfoController {

	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/role")
	public Set<Role> getLoggedInUserRole(Principal p)
	{
		User user=userService.getOneUserByName(p.getName());
		return user.getRoles();
	}
}
