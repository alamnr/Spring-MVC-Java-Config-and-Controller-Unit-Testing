package com.spring.mvc.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.mvc.test.security.model.AppUser;


@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUserName(String username);
}
