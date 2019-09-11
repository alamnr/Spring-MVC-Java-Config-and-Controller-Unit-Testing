package com.spring.mvc.test.security.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String roleName;
	
	@ManyToMany(mappedBy="roles")
	private Set<AppUser> users;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Set<AppUser> getUsers() {
		return users;
	}

	public void setUsers(Set<AppUser> users) {
		this.users = users;
	}
	
	
	@Override
	public String toString() {
		return "Role [roleId=" + id + ", roleName=" + roleName + ", users=" + users + "]";
	}
	
	

}
