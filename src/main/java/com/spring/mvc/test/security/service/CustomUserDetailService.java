package com.spring.mvc.test.security.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.test.repository.AppUserRepository;
import com.spring.mvc.test.security.model.AppUser;

@Component("customUserDetailService")
@Transactional
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	AppUserRepository appUserRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserRepository.findByUserName(username);
		return new User(user.getUserName(),user.getPassword(), 
				AuthorityUtils.commaSeparatedStringToAuthorityList(
						user.getRoles().stream().map(obj->obj.getRoleName()).collect(Collectors.joining(","))));
		//return user;
	}

}
