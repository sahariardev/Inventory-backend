package com.sahariar.InventoryKnitGarden.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	JWTProvider jwtprovider;
	
	@Autowired
	UserDetailsServiceImpl userDetail;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		try {
			 
			String jwt = getJwt(request);
			if (jwt != null && jwtprovider.validateJwtToken(jwt)) {
				String username = jwtprovider.getUserNameFromJwtToken(jwt);
 
				UserDetails userDetails = userDetail.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
 
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Can NOT set user authentication -> Message: {}", e);
		}
 
		filterChain.doFilter(request, response); 
		

	}
	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
 
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
 
		return null;
	}

}
