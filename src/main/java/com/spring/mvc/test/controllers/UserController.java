package com.spring.mvc.test.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.mvc.test.dto.TodoDTO;
import com.spring.mvc.test.dto.UserDTO;
import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.security.model.AppUser;
import com.spring.mvc.test.security.model.Role;
import com.spring.mvc.test.service.TodoService;
import com.spring.mvc.test.service.UserService;
import com.spring.mvc.test.exception.EntityNotFoundException;

@Controller
@SessionAttributes("user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	protected static final String FEEDBACK_MESSAGE_KEY_ADDED = "feedback.message.added";
	protected static final String FEEDBACK_MESSAGE_KEY_UPDATED = "feedback.message.updated";
	protected static final String FEEDBACK_MESSAGE_KEY_DELETED = "feedback.message.deleted";
	protected static final String FLASH_MESSAGE_KEY_FEEDBACK = "feedbackMessage";

	protected static final String MODEL_ATTRIBUTE = "user";
	protected static final String MODEL_ATTRIBUTE_LIST = "users";

	protected static final String PARAMETER_ID = "id";

	protected static final String REQUEST_MAPPING_LIST = "/users";
	protected static final String REQUEST_MAPPING_VIEW = "/user/{id}";

	protected static final String VIEW_ADD = "user/user_add";
	protected static final String VIEW_LIST = "user/user_list";
	protected static final String VIEW_UPDATE = "user/user_update";
	protected static final String VIEW_DETAIL = "user/user_details";

	private final UserService service;

	private final MessageSource messageSource;

	@Autowired
	public UserController(UserService service, MessageSource messageSource) {
		super();
		this.service = service;
		this.messageSource = messageSource;
	}

	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		LOGGER.debug("Rendering add user entry form.");
		UserDTO formObject = new UserDTO();
        model.addAttribute(MODEL_ATTRIBUTE, formObject);

		return "user/user_add";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("user") @Valid UserDTO userDTO, Errors errors, SessionStatus sessionStatus,
			Model model, RedirectAttributes attributes) {
		LOGGER.debug("Adding a new User entry with information: {}", userDTO);
		if (errors.hasErrors()) {
			LOGGER.debug("Add user form was submitted with binding errors. Rendering form view.");
			return "user/user_add";
		}

		AppUser added = this.service.addUser(userDTO);
		//System.out.println("Saving user");
		sessionStatus.setComplete();
		addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_ADDED, added.getUserName());
		attributes.addAttribute(PARAMETER_ID, added.getId());

		return createRedirectViewPath(REQUEST_MAPPING_VIEW);

	}
	

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes attributes) throws EntityNotFoundException {
        LOGGER.debug("Deleting a user entry with id: {}", id);

        AppUser deleted = service.deleteById(id);
        LOGGER.debug("Deleted user entry with information: {}", deleted);

        addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getUserName());

        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }
    
    @RequestMapping(value = REQUEST_MAPPING_LIST, method = RequestMethod.GET)
	public String findAllUser(Model model) {
		LOGGER.debug("Rendering user list.");
		List<AppUser> models = service.findAllUser();
        LOGGER.debug("Found {} user entries.", models.size());

		model.addAttribute(MODEL_ATTRIBUTE_LIST, models);
		
		return VIEW_LIST;
	}

	@RequestMapping(value = REQUEST_MAPPING_VIEW, method=RequestMethod.GET)
	public String findById(@PathVariable("id") Long userId, Model model) throws EntityNotFoundException {
		LOGGER.debug("Rendering user page for user entry with id: {}", userId);

        AppUser found = service.findById(userId);
        LOGGER.debug("Found to-do entry with information: {}", found);

        model.addAttribute(MODEL_ATTRIBUTE, found);

		return VIEW_DETAIL;
	}

	@RequestMapping(value = "/user/update/{id}", method = RequestMethod.GET)
    public String showUpdateUserForm(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        LOGGER.debug("Rendering update user entry form for user entry with id: {}", id);

        AppUser updated = service.findById(id);
        LOGGER.debug("Rendering update user form for user with information: {}", updated);

        UserDTO formObject = constructFormObjectForUpdateForm(updated);
        model.addAttribute(MODEL_ATTRIBUTE, formObject);

        return VIEW_UPDATE;
    }
	
	 @RequestMapping(value = "/user/update", method = RequestMethod.POST)
	    public String update(@Valid @ModelAttribute(MODEL_ATTRIBUTE) UserDTO dto, BindingResult result, RedirectAttributes attributes) throws EntityNotFoundException {
	        LOGGER.debug("Updating a user entry with information: {}", dto);

	        if (result.hasErrors()) {
	            LOGGER.debug("Update user entry form was submitted with validation errors. Rendering form view.");
	            return VIEW_UPDATE;
	        }

	        AppUser updated = service.update(dto);
	        LOGGER.debug("Updated the information of a entry to: {}", updated);

	        addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_UPDATED, updated.getUserName());
	        attributes.addAttribute(PARAMETER_ID, updated.getId());

	        return createRedirectViewPath(REQUEST_MAPPING_VIEW);
	    }

	private UserDTO constructFormObjectForUpdateForm(AppUser updated) {
		UserDTO dto = new UserDTO();

		dto.setId(updated.getId());
		dto.setEmail(updated.getEmail());
		dto.setFirstName(updated.getFirstName());
		dto.setLastName(updated.getLastName());
		dto.setUserName(updated.getUserName());
		dto.setPassword(updated.getPassword());
		dto.setRoles(updated.getRoles().stream().map(obj->obj.getId()).collect(Collectors.toList()));

		return dto;
	}

	private void addFeedbackMessage(RedirectAttributes attributes, String messageCode, Object... messageParameters) {
		LOGGER.debug("Adding feedback message with code: {} and params: {}", messageCode, messageParameters);
		String localizedFeedbackMessage = getMessage(messageCode, messageParameters);
		LOGGER.debug("Localized message is: {}", localizedFeedbackMessage);
		attributes.addFlashAttribute(FLASH_MESSAGE_KEY_FEEDBACK, localizedFeedbackMessage);
	}

	private String getMessage(String messageCode, Object... messageParameters) {
		Locale current = LocaleContextHolder.getLocale();
		LOGGER.debug("Current locale is {}", current);
		return messageSource.getMessage(messageCode, messageParameters, current);
	}

	private String createRedirectViewPath(String requestMapping) {
		StringBuilder redirectViewPath = new StringBuilder();
		redirectViewPath.append("redirect:");
		redirectViewPath.append(requestMapping);
		return redirectViewPath.toString();
	}
	
	@ModelAttribute("roleOptions")
	//public Map<String,String> getTypes(){
	public List<Role> getRoles(){
		/*Map<String, String> rolesMap = new LinkedHashMap<String, String>();
		service.findAllRole().stream().forEach(obj->rolesMap.put(obj.getId().toString(), obj.getRoleName()));*/
		
		return service.findAllRole();
	}

}
