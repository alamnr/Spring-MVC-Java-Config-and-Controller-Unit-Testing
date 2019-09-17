package com.spring.mvc.test.security.ldap;

import java.util.Collection;

import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import com.spring.mvc.test.security.model.AppUser;


@Component("contextMapper")
public class CustomUserDetailsContextMapper implements UserDetailsContextMapper {

	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		AppUser appUser = new AppUser();
		appUser.setFirstName(ctx.getStringAttribute("givenName"));
		appUser.setLastName(ctx.getStringAttribute("sn"));
		appUser.setEmail(ctx.getStringAttribute("mail"));
		appUser.setUserName(username);
		appUser.setAuthorities(authorities);
		return appUser;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		AppUser appUser = (AppUser)user;
		
		ctx.setAttributeValue("givenName", appUser.getFirstName());
		ctx.setAttributeValue("sn", appUser.getLastName());
		ctx.setAttributeValue("mail", appUser.getEmail());
		ctx.setAttributeValue("password", appUser.getPassword());
		ctx.setAttributeValue("uid", appUser.getUserName());

	}

}
