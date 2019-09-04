package com.spring.mvc.test.controllers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.mvc.test.config.spring5.MvcWebConfig;
import com.spring.mvc.test.context.TestContext;
import com.spring.mvc.test.service.TodoService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestContext.class,MvcWebConfig.class})
//@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:spring/dispatcher-servlet.xml"})
@WebAppConfiguration
public class WebApplicationContextTodoControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private TodoService todoServiceMock;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeAll
	public void setUp()
	{
		//We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(todoServiceMock);
 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
