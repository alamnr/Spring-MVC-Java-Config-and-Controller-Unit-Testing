package com.spring.mvc.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.mvc.test.security.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Role findByRoleName(String roleName);
	
	@Query("select r from Role r where r.id in ?1 ")
	public List<Role> findRoleByIds(List<Long> ids);
}
