package com.springboot.security.jwt.demo.models;

public class AuthenticationResponse {

	private String jwt;

	public AuthenticationResponse() {
		super();
	}

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	
	
	
	
}
