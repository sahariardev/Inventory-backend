package com.sahariar.InventoryKnitGarden.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

@Service
public class AuthEntryPoint implements AuthenticationEntryPoint {

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
       
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer=response.getWriter();
		writer.println("Sorry you are not authorized to use this service.");
	}

}
