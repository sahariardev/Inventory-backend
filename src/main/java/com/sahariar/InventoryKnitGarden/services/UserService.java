package com.sahariar.InventoryKnitGarden.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	public User  getOneUserByName(String username)
	{
		return userRepository.findUserByUsername(username);
	}
}
