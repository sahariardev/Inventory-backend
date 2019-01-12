package com.sahariar.InventoryKnitGarden.controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sahariar.InventoryKnitGarden.models.User;
import com.sahariar.InventoryKnitGarden.repositories.RoleRepository;
import com.sahariar.InventoryKnitGarden.repositories.UserRepository;
import com.sahariar.InventoryKnitGarden.requests.LoginRequest;
import com.sahariar.InventoryKnitGarden.response.JWTResponse;
import com.sahariar.InventoryKnitGarden.services.JWTProvider;

@CrossOrigin
@RestController("api/auth")
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
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
 
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
 
		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
 
		
 
		user.setRoles(roles);
		userRepository.save(user);
 
		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	
}
