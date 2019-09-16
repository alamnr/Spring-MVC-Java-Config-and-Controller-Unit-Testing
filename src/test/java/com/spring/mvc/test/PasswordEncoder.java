package com.spring.mvc.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		String encodedPassword = encoder.encode("password");
		System.out.println(encodedPassword);
		
		StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder();
		System.out.println(standardPasswordEncoder.encode("password"));
		
	}
}
