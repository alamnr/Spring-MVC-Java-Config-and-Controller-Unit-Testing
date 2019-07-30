package com.spring.mvc.test.context.springOld;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomDispatcherServlet implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		/*
		AnnotationConfigWebApplicationContext dispatcherServletContext = new AnnotationConfigWebApplicationContext();
		
		dispatcherServletContext.register(DispatcherServletContext.class);
		
		DispatcherServlet dispatcherServlet  =  new DispatcherServlet(dispatcherServletContext);
		
		// Create a servlet dynamically
		
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcherServlet", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		
		// Add servlet mapping url
		dispatcher.addMapping("/");
	
*/
	}

}
