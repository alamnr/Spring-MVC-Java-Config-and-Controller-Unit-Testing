package com.spring.mvc.test.accessDeniedHander;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null)
		{
			LOGGER.warn("User: "+ authentication.getName() +" attempted to access the protected url: "
					+ request.getRequestURI());
		}
		response.sendRedirect(request.getContextPath() + "/accessDenied");
	}

}