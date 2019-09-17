package com.spring.mvc.test.util;

import java.util.List;

import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Component;

import com.spring.mvc.test.security.model.AppUser;

@Component
public class UserUtil {

	
	@PreFilter("principal.userName == filterObject.userName")
	public String getEmails(List<Object> list)
	{
		StringBuilder sb = new StringBuilder();
		for (Object appUser : list) {
			
			sb.append(((AppUser)appUser).getEmail());
			sb.append("  ");
		}
		
		return sb.toString();
	}
}
