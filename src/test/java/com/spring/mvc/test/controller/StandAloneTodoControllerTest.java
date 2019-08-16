package com.spring.mvc.test.controller;

import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.spring.mvc.test.controllers.TodoController;
import com.spring.mvc.test.service.TodoService;

@ExtendWith(MockitoExtension.class)
public class StandAloneTodoControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private TodoService todoServiceMock;
	
	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new TodoController(todoServiceMock, messageSource()))
					.setHandlerExceptionResolvers(exceptionResolver())
					//.setValidator(validator())
					.setViewResolvers(viewResolver())
					.build();
	}
	
	private HandlerExceptionResolver exceptionResolver()
	{
		SimpleMappingExceptionResolver exceptionResolver  =   new SimpleMappingExceptionResolver();
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
	 
	        messageSource.setBasename("i18n/welcome");
	        messageSource.setUseCodeAsDefaultMessage(true);
	 
	        return messageSource;
	    }
	 /*
	 private Validator validator() {
	        return new LocalValidatorFactoryBean();
	    }
	 */
	 private ViewResolver viewResolver() {
	        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	 
	        viewResolver.setViewClass(JstlView.class);
	        viewResolver.setPrefix("/WEB-INF/views/");
	        viewResolver.setSuffix(".jsp");
	 
	        return viewResolver;
	    }
	 
}
