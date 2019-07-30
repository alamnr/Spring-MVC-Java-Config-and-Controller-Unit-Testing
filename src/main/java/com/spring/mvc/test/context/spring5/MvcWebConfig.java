package com.spring.mvc.test.context.spring5;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/*
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.spring.mvc.test.controllers"})*/
public class MvcWebConfig implements WebMvcConfigurer {

   @Override
   public void configureViewResolvers(ViewResolverRegistry registry) {
      registry.jsp("/WEB-INF/views/", ".jsp");
   }
   
   /*@Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
   }*/
 

   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {

      // Register resource handler for CSS and JS
      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());

      // Register resource handler for images
      registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/images/")
            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
   }
   
   
   
   @Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver()
	{
		SimpleMappingExceptionResolver simpleMappingExceptionResolver  =  new SimpleMappingExceptionResolver();
		
		Properties exceptionMappings = new Properties();
		exceptionMappings.put("com.spring.mvc.test.exception.TodoNotFoundException", "error/404");
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");
		
		simpleMappingExceptionResolver.setExceptionMappings(exceptionMappings);
		
		Properties statusCodes  = new Properties();
		
		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");
		
		simpleMappingExceptionResolver.setStatusCodes(statusCodes);
	
		return  simpleMappingExceptionResolver;
	}
}