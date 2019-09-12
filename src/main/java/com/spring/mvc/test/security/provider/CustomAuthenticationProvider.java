package com.spring.mvc.test.security.provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.spring.mvc.test.repository.AppUserRepository;
import com.spring.mvc.test.security.model.AppUser;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AppUserRepository appUserRepository;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)authentication;
		AppUser  user = appUserRepository.findByUserName(token.getName());
		if(!user.getPassword().equalsIgnoreCase(token.getCredentials().toString())) {
			throw new BadCredentialsException("password is invalid");
		}
		return new UsernamePasswordAuthenticationToken(user, user.getPassword(),user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
	
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}

}
