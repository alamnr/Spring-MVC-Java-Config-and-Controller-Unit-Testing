package com.spring.mvc.test.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.spring.mvc.test.dto.TodoDTO;
import com.spring.mvc.test.dto.UserDTO;
import com.spring.mvc.test.exception.EntityNotFoundException;
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

	public AppUser addUser(@Valid UserDTO userDTO) {
		LOGGER.debug("Adding a new user entry with information: {}", userDTO);

        AppUser model = AppUser.getBuilder(userDTO.getUserName(),userDTO.getPassword())
                .email(userDTO.getEmail())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .build();
                
		
		return appUserRepository.save(model);
	}

	public AppUser deleteById(Long id) throws EntityNotFoundException {
		LOGGER.debug("Deleting a user entry with id: {}", id);

        Optional<AppUser> deleted = appUserRepository.findById(id);
        LOGGER.debug("Deleting user entry: {}", deleted);
        appUserRepository.delete(deleted.get());
        return deleted.get();
	}

	public List<AppUser> findAll() {
		
		return appUserRepository.findAll();
	}

	public AppUser findById(Long id) throws EntityNotFoundException {
				
		AppUser found = appUserRepository.getOne(id);
		
		LOGGER.debug("find user by id: {}", found);
		
		if (found == null) {
            throw new EntityNotFoundException("No entry found with id: " + id);
        }
		
		return  found;		
	}
	
	public AppUser update(UserDTO updated) throws EntityNotFoundException {
		LOGGER.debug("Updating entity with information: {}", updated);

        Optional<AppUser> model = appUserRepository.findById(updated.getId());
        LOGGER.debug("Found a user entry: {}", model);
        
        model.get().update(updated.getPassword(), updated.getFirstName(),updated.getLastName(),updated.getEmail());
        
        appUserRepository.save(model.get());

        return model.get();

	}

	
}
