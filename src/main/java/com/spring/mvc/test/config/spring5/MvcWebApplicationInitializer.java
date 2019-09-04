package com.spring.mvc.test.config.spring5;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public abstract class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new ConfigurableSiteMeshFilter() };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MyApplicationContext.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}*/

}