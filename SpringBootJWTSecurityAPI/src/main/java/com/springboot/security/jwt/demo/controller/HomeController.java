package com.springboot.security.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.security.jwt.demo.models.AuthenticationRequest;
import com.springboot.security.jwt.demo.models.AuthenticationResponse;
import com.springboot.security.jwt.demo.service.AppUsersDetailsService;
import com.springboot.security.jwt.demo.util.JWTUtil;

@RestController
public class HomeController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUsersDetailsService appUsersDetailsService;
	@Autowired
	private JWTUtil jWTUtil;
	
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/getJWTToken", method=RequestMethod.POST)
	public ResponseEntity<?> getJWTToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
	try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken 
			= new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);		
	} catch (BadCredentialsException e) {
		throw new Exception("Incorrect UserName or Password", e);
	}
		
	final UserDetails userDetails = appUsersDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	final String jwtString = jWTUtil.generateToken(userDetails);
	
	return ResponseEntity.ok(new AuthenticationResponse(jwtString));
	
	}
}
