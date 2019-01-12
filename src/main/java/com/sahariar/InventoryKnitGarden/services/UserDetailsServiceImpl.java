package com.sahariar.InventoryKnitGarden.services;



import java.nio.file.attribute.UserPrincipal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sahariar.InventoryKnitGarden.models.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userService.getOneUserByName(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("");
		}
		return UserPrinciple.build(user);
	}

}
