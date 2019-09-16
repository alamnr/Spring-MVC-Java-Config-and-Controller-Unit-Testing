package com.spring.mvc.test.controllers;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
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
import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.service.TodoService;
import com.spring.mvc.test.exception.EntityNotFoundException;

@Controller
@SessionAttributes("todo")
public class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

	protected static final String FEEDBACK_MESSAGE_KEY_ADDED = "feedback.message.added";
	protected static final String FEEDBACK_MESSAGE_KEY_UPDATED = "feedback.message.updated";
	protected static final String FEEDBACK_MESSAGE_KEY_DELETED = "feedback.message.deleted";
	protected static final String FLASH_MESSAGE_KEY_FEEDBACK = "feedbackMessage";

	protected static final String MODEL_ATTRIBUTE = "todo";
	protected static final String MODEL_ATTRIBUTE_LIST = "todos";

	protected static final String PARAMETER_ID = "id";

	protected static final String REQUEST_MAPPING_LIST = "/todos";
	protected static final String REQUEST_MAPPING_VIEW = "/todo/{id}";

	protected static final String VIEW_ADD = "todo/todo_add";
	protected static final String VIEW_LIST = "todo/todo_list";
	protected static final String VIEW_UPDATE = "todo/todo_update";
	protected static final String VIEW_DETAIL = "todo/todo_details";

	private final TodoService service;

	private final MessageSource messageSource;

	@Autowired
	public TodoController(TodoService service, MessageSource messageSource) {
		super();
		this.service = service;
		this.messageSource = messageSource;
	}

	
	@RequestMapping(value = "/todo/add", method = RequestMethod.GET)
	public String showAddTodoForm(Model model) {
		LOGGER.debug("Rendering add to-do entry form.");
		TodoDTO formObject = new TodoDTO();
        model.addAttribute(MODEL_ATTRIBUTE, formObject);

		return "todo/todo_add";
	}

	@RequestMapping(value = "/todo/add", method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") @Valid TodoDTO todoDTO, Errors errors, SessionStatus sessionStatus,
			Model model, RedirectAttributes attributes) {
		LOGGER.debug("Adding a new to-do entry with information: {}", todoDTO);
		if (errors.hasErrors()) {
			LOGGER.debug("Add to-do form was submitted with binding errors. Rendering form view.");
			return "todo/todo_add";
		}

		Todo added = this.service.addTodo(todoDTO);
		System.out.println("Saving todo");
		sessionStatus.setComplete();
		addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_ADDED, added.getTitle());
		attributes.addAttribute(PARAMETER_ID, added.getId());

		return createRedirectViewPath(REQUEST_MAPPING_VIEW);

	}
	

    @RequestMapping(value = "/todo/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes attributes) throws EntityNotFoundException {
        LOGGER.debug("Deleting a to-do entry with id: {}", id);

        Todo deleted = service.deleteById(id);
        LOGGER.debug("Deleted to-do entry with information: {}", deleted);

        addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_DELETED, deleted.getTitle());

        return createRedirectViewPath(REQUEST_MAPPING_LIST);
    }
    
    @RequestMapping(value = REQUEST_MAPPING_LIST, method = RequestMethod.GET)
	public String findAllTodo(Model model) {
		LOGGER.debug("Rendering to-do list.");
		List<Todo> models = service.findAll();
        LOGGER.debug("Found {} to-do entries.", models.size());

		model.addAttribute(TodoController.MODEL_ATTRIBUTE_LIST, models);
		
		return VIEW_LIST;
	}

	@RequestMapping(value = REQUEST_MAPPING_VIEW, method=RequestMethod.GET)
	public String findById(@PathVariable("id") Long todoId, Model model) throws EntityNotFoundException {
		LOGGER.debug("Rendering to-do page for to-do entry with id: {}", todoId);

        Todo found = service.findById(todoId);
        LOGGER.debug("Found to-do entry with information: {}", found);

        model.addAttribute(MODEL_ATTRIBUTE, found);

		return VIEW_DETAIL;
	}

	@RequestMapping(value = "/todo/update/{id}", method = RequestMethod.GET)
    public String showUpdateTodoForm(@PathVariable("id") Long id, Model model) throws EntityNotFoundException {
        LOGGER.debug("Rendering update to-do entry form for to-do entry with id: {}", id);

        Todo updated = service.findById(id);
        LOGGER.debug("Rendering update to-do form for to-do with information: {}", updated);

        TodoDTO formObject = constructFormObjectForUpdateForm(updated);
        model.addAttribute(MODEL_ATTRIBUTE, formObject);

        return VIEW_UPDATE;
    }
	
	 @RequestMapping(value = "/todo/update", method = RequestMethod.POST)
	    public String update(@Valid @ModelAttribute(MODEL_ATTRIBUTE) TodoDTO dto, BindingResult result, RedirectAttributes attributes) throws EntityNotFoundException {
	        LOGGER.debug("Updating a to-do entry with information: {}", dto);

	        if (result.hasErrors()) {
	            LOGGER.debug("Update to-do entry form was submitted with validation errors. Rendering form view.");
	            return VIEW_UPDATE;
	        }

	        Todo updated = service.update(dto);
	        LOGGER.debug("Updated the information of a to-entry to: {}", updated);

	        addFeedbackMessage(attributes, FEEDBACK_MESSAGE_KEY_UPDATED, updated.getTitle());
	        attributes.addAttribute(PARAMETER_ID, updated.getId());

	        return createRedirectViewPath(REQUEST_MAPPING_VIEW);
	    }

	private TodoDTO constructFormObjectForUpdateForm(Todo updated) {
		TodoDTO dto = new TodoDTO();

		dto.setId(updated.getId());
		dto.setDescription(updated.getDescription());
		dto.setTitle(updated.getTitle());

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
	
	@ModelAttribute("isUser")
	public boolean isUser(Authentication auth)
	{
		return auth !=null && auth.getAuthorities().contains(AuthorityUtils.createAuthorityList("ROLE_USER").get(0));
	}

}
