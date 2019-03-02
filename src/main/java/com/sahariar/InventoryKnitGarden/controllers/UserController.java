package com.sahariar.InventoryKnitGarden.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.Role;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.response.MenuItemAuthorizations;
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
	
	@GetMapping("api/users/menuitems")
	public MenuItemAuthorizations menuItems(Principal principal)
	{
	   
		User user=userService.getOneUserByName(principal.getName());
	    Set<Role> roles=user.getRoles();
	    MenuItemAuthorizations auth=new MenuItemAuthorizations();
	    
	    for(Role role:roles)
	    {
	    	if(role.getRole().equals("admin"))
	    	{
	    		auth.setBooking(true);
	    		auth.setBuyer(true);
	    		auth.setCategory(true);
	    		auth.setInventory(true);
	    		auth.setInventoryItems(true);
	    		auth.setLocation(true);
	    		auth.setProject(true);
	    		auth.setPurchase(true);
	    		auth.setStage(true);
	    		auth.setStyle(true);
	    		auth.setUnit(true);
	    	}
	    	else if(role.getRole().equals("store_manager"))
	    	{
	    		auth.setBooking(true);
	    		auth.setInventory(true);
	    		auth.setPurchase(true);

	    	}
	    	else if (role.getRole().equals("merchandiser"))
	    	{
	    		auth.setBooking(true);
	    		auth.setInventory(true);
	    		auth.setInventoryItems(true);
	    		auth.setProject(true);
	    		auth.setPurchase(true);
	    		auth.setStyle(true);
	    		auth.setUnit(true);
	    	}
	    	else if (role.getRole().equals("managing_director"))
	    	{
	    		auth.setBooking(true);
	    		auth.setInventory(true);
	    		auth.setProject(true);
	    		auth.setPurchase(true);
	    		auth.setStyle(true);

	    	}
	    	else if (role.getRole().equals("general_manager"))
	    	{
	    		auth.setBooking(true);
	    		auth.setBuyer(true);
	    		auth.setInventory(true);
	    		auth.setInventoryItems(true);
	    		auth.setProject(true);
	    		auth.setPurchase(true);
	    		auth.setStyle(true);
	    		auth.setUnit(true);
	    	}
	    	
	    }
	    return auth;
	   
	   
	}
	
	
	
	
	
	
}
