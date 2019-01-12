package com.sahariar.InventoryKnitGarden.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.Role;
import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.repositories.RoleRepository;
import com.sahariar.InventoryKnitGarden.repositories.UserRepository;
import com.sahariar.InventoryKnitGarden.requests.LoginRequest;
import com.sahariar.InventoryKnitGarden.requests.SignupRequest;
import com.sahariar.InventoryKnitGarden.response.JWTResponse;
import com.sahariar.InventoryKnitGarden.services.JWTProvider;

@CrossOrigin
@RestController()
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder endocder;
	
	@Autowired
	JWTProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/hello")
	public String hello()
	{
		return "Hello";
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
 
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
		return ResponseEntity.ok(new JWTResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		System.out.println("Hey I am here ");
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity("username already exist",HttpStatus.BAD_REQUEST);
		}
		System.out.println("Hey I am also here ");
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity("email already exist ",HttpStatus.BAD_REQUEST);
		}
		System.out.println("Hey I am here again ");
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
 
		
 
		Set<String> roles=signUpRequest.getRole();
		for(String role :roles)
		{
			Role userrole=roleRepository.findByRole(role);
			user.getRoles().add(userrole);
		}
		
		userRepository.save(user);
 
		return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
	}
	
	
}
