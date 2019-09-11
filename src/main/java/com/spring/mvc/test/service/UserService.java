package com.spring.mvc.test.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.test.dto.UserDTO;
import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.repository.AppUserRepository;
import com.spring.mvc.test.repository.RoleRepository;
import com.spring.mvc.test.security.model.AppUser;
import com.spring.mvc.test.security.model.Role;

@Service
@Transactional
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	
	private final AppUserRepository appUserRepository;
	
	
	private final RoleRepository roleRepository;

	@Autowired
	public UserService(AppUserRepository appUserRepository, RoleRepository roleRepository) {
		super();
		this.appUserRepository = appUserRepository;
		this.roleRepository = roleRepository;
	}
	
	public void createUser(UserDTO userDto) {
		
		LOGGER.debug("Registering a new user entry with information: {}", userDto);

        AppUser model = AppUser.getBuilder(userDto.getUserName(),userDto.getPassword())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
		Set<Role> roleSet = new HashSet<>();
		roleSet.add(roleRepository.findByRoleName("ROLE_USER"));
		model.setRoles(roleSet);
		
		AppUser appUser = appUserRepository.save(model);
		
		User userDetails= new User(appUser.getUserName(),appUser.getPassword(), 
				AuthorityUtils.commaSeparatedStringToAuthorityList(
						appUser.getRoles().stream().map(obj->obj.getRoleName()).collect(Collectors.joining(","))));
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
				userDetails.getAuthorities());
		
		/*Authentication authentication = new UsernamePasswordAuthenticationToken(appUser, appUser.getPassword(),
				appUser.getAuthorities());*/
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
}
