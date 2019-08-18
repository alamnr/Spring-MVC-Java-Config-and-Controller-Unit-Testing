package com.spring.mvc.test.controllers;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Properties;

import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring.mvc.test.TestUtil;
import com.spring.mvc.test.WebTestConstants;
import com.spring.mvc.test.controllers.TodoController;
import com.spring.mvc.test.dto.TodoDTO;
import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.service.TodoService;

@ExtendWith(MockitoExtension.class)
public class StandAloneTodoControllerTest {

	private static final String DESCRIPTION = "description";
	private static final Long ID = 1L;
	private static final String TITLE = "title";

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/welcome";
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

	private MockMvc mockMvc;

	@Mock
	private TodoService todoServiceMock;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new TodoController(todoServiceMock, messageSource()))
				.setHandlerExceptionResolvers(exceptionResolver())
				// .setValidator(validator())
				.setViewResolvers(viewResolver()).build();
	}

	private HandlerExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		Properties exceptionMappings = new Properties();

		exceptionMappings.put("com.spring.mvc.test.exception.TodoNotFoundException", "error/404");
		exceptionMappings.put("java.lang.exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");

		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();

		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");

		exceptionResolver.setStatusCodes(statusCodes);

		return exceptionResolver;
	}

	private MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

		messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
		messageSource.setUseCodeAsDefaultMessage(true);

		return messageSource;
	}

	/*
	 * private Validator validator() { return new LocalValidatorFactoryBean(); }
	 */
	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
		viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);

		return viewResolver;
	}

	@Test
	public void showAddTodoForm_ShouldCreateFormObjectAndRenderAddTodoForm() throws Exception {
		mockMvc.perform(get("/todo/add")).andExpect(status().isOk())
				.andExpect(view().name(TodoController.VIEW_TODO_ADD))
				.andExpect(forwardedUrl("/WEB-INF/views/todo/todo_add.jsp"))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO, hasProperty("id", nullValue())))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO,
						hasProperty("description", isEmptyOrNullString())))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO,
						hasProperty("title", isEmptyOrNullString())));

		verifyZeroInteractions(todoServiceMock);
	}

	@Test
	public void add_EmptyTodoEntry_ShouldRenderFormViewAndReturnValidationErrorForTitle() throws Exception {

		mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr(TodoController.MODEL_ATTRIBUTE_TODO, new TodoDTO())).andExpect(status().isOk())
				.andExpect(view().name("todo/todo_add")).andExpect(forwardedUrl("/WEB-INF/views/todo/todo_add.jsp"))
				.andExpect(model().attributeHasFieldErrors(TodoController.MODEL_ATTRIBUTE_TODO, "title"))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO, hasProperty("id", nullValue())))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO,
						hasProperty("title", isEmptyOrNullString())))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO,
						hasProperty("description", isEmptyOrNullString())));
		verifyZeroInteractions(todoServiceMock);
	}

	@Test
	public void add_DescriptionAndTitleAreTooLong_ShouldRenderFormViewAndReturnValidationErrorsForTitleAndDescription()
			throws Exception {
		String title = TestUtil.createStringWithLength(Todo.MAX_LENGTH_TITLE + 1);
		String description = TestUtil.createStringWithLength(Todo.MAX_LENGTH_DESCRIPTION + 1);

		mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.sessionAttr(TodoController.MODEL_ATTRIBUTE_TODO, new TodoDTO())
				.param(WebTestConstants.FORM_FIELD_TITLE, title)
				.param(WebTestConstants.FORM_FIELD_DESCRIPTION, description)).andExpect(status().isOk())
				.andExpect(view().name("todo/todo_add")).andExpect(forwardedUrl("/WEB-INF/views/todo/todo_add.jsp"))
				.andExpect(model().attributeHasFieldErrors(TodoController.MODEL_ATTRIBUTE_TODO, "title"))
				.andExpect(model().attributeHasFieldErrors(TodoController.MODEL_ATTRIBUTE_TODO, "description"))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO, hasProperty("id", nullValue())))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO, hasProperty("title", is(title))))
				.andExpect(model().attribute(TodoController.MODEL_ATTRIBUTE_TODO,
						hasProperty("description", is(description))));

		verifyZeroInteractions(todoServiceMock);

	}

	@Test
	public void add_NewTodoEntry_ShouldAddTodoEntryAndRenderViewTodoEntryView() throws Exception {

		Todo added = Todo.getBuilder(TITLE).description(DESCRIPTION).build();
		added.setId(ID);

		when(todoServiceMock.addTodo(isA(TodoDTO.class))).thenReturn(added);

		String expectedRedirectViewPath = TestUtil.createRedirectViewPath(TodoController.REQUEST_MAPPING_TODO_VIEW);

		mockMvc.perform(post("/todo/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param(WebTestConstants.FORM_FIELD_DESCRIPTION, "description")
				.param(WebTestConstants.FORM_FIELD_TITLE, "title")
				.sessionAttr(TodoController.MODEL_ATTRIBUTE_TODO, new TodoDTO()))

				.andExpect(status().isMovedTemporarily()).andExpect(view().name(expectedRedirectViewPath))
				.andExpect(redirectedUrl("/todo/1"))
				.andExpect(model().attribute(TodoController.PARAMETER_TODO_ID, is(ID.toString()))).andExpect(flash()
						.attribute(TodoController.FLASH_MESSAGE_KEY_FEEDBACK, is("Todo entry: title was added.")));

		ArgumentCaptor<TodoDTO> formObjectArgument = ArgumentCaptor.forClass(TodoDTO.class);
		verify(todoServiceMock, times(1)).addTodo(formObjectArgument.capture());
		verifyNoMoreInteractions(todoServiceMock);

		TodoDTO formObject = formObjectArgument.getValue();

		assertEquals(formObject.getDescription(), DESCRIPTION);
		assertNull(formObject.getId());
		assertEquals(formObject.getTitle(), TITLE);
	}
	
	@Test
	public void deleteById_TodoEntryFound_ShouldDeleteTodoEntryAndRenderTodoListView()  throws Exception {
		
		Todo deleted = Todo.getBuilder("Foo").description(DESCRIPTION).build();
		deleted.setId(ID);
		
		when(todoServiceMock.deleteById(ID)).thenReturn(deleted);
		
		String expectedRedirectViewPath = TestUtil.createRedirectViewPath(TodoController.REQUEST_MAPPING_TODO_LIST);
		
		mockMvc.perform(get("/todo/delete/{id}",ID))
				.andExpect(status().isMovedTemporarily())
				.andExpect(view().name(expectedRedirectViewPath))
		  		.andExpect(flash().attribute(TodoController.FLASH_MESSAGE_KEY_FEEDBACK, is("Todo entry: Foo was deleted.")));
		
		
		verify(todoServiceMock, times(1)).deleteById(ID);
		verifyNoMoreInteractions(todoServiceMock);
	}
}
