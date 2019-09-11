package com.spring.mvc.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mvc.test.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
