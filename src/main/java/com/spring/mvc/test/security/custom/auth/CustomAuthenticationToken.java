package com.spring.mvc.test.security.custom.auth;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.spring.mvc.test.security.model.AppUser;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
	
	private String make;

	public CustomAuthenticationToken(String principal, String credentials, String make) {
		super(principal, credentials);
		this.make = make;
	}
	
	

	public CustomAuthenticationToken(AppUser principal, String credentials,
			Collection<? extends GrantedAuthority> authorities, String make) {
		super(principal, credentials, authorities);
		this.make = make;
	}



	public String getMake() {
		return make;
	}	
	

}
