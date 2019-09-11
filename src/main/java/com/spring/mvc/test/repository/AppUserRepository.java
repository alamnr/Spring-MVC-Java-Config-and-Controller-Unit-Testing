package com.spring.mvc.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.test.security.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUserName(String username);
}
