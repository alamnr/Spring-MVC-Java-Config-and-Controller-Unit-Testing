package com.spring.mvc.test.context;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.spring.mvc.test.service.TodoService;

@Configuration
public class TestContext {
	
	@Bean
	public MessageSource messageSource()
	{
		ResourceBundleMessageSource messageSource  = new ResourceBundleMessageSource();
		messageSource.setBasename("classpath:i18n/welcome");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;	
	}
	
	@Bean
	public TodoService todoService() {
		return Mockito.mock(TodoService.class);
	}

}
