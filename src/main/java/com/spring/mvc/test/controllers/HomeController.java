package com.spring.mvc.test.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.collections.SetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.test.dto.TodoDTO;
import com.spring.mvc.test.dto.UserDTO;
import com.spring.mvc.test.repository.AppUserRepository;
import com.spring.mvc.test.repository.RoleRepository;
import com.spring.mvc.test.security.model.AppUser;
import com.spring.mvc.test.security.model.Role;
import com.spring.mvc.test.service.UserService;

@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	private final UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/home")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/")
	public String welcome() {
		return "welcome";
	}

	
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String showLoginForm()
	{
		return "login/loginForm";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		LOGGER.debug("Rendering register user entry form.");
		UserDTO formObject = new UserDTO();
        model.addAttribute("user", formObject);

		return "login/register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String createAccount(@ModelAttribute("user") @Valid UserDTO userDto, Errors errors)
	{	
		LOGGER.debug("Register a new user entry with information: {}", userDto);
		if (errors.hasErrors()) {
			LOGGER.debug("Register user form was submitted with binding errors. Rendering form view.");
			return "login/register";
		}
		userService.createUser(userDto);
		
		return "redirect:/todos";
	}
	

}
