package com.spring.mvc.test.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.userdetails.User;

import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.model.Todo.Builder;
import com.spring.mvc.test.security.model.AppUser;
import com.spring.mvc.test.security.model.Role;

public class UserDTO {

	private Long id;

	@Length(max = AppUser.MAX_LENGTH_EMAIL)
	private String email;

	private String firstName;

	private String lastName;
	
	@NotEmpty
	@Length(max = AppUser.MAX_LENGTH_USERNAME)
	private String userName;

	@NotEmpty
	private String password;

	private Set<Role> roles;


	public UserDTO() {

	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Set<Role> getRoles() {
		return roles;
	}



	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



	public static Builder getBuilder(String username,String password) {
		return new Builder(username,password);
	}

		
	public static class Builder {
		private UserDTO built;

		public Builder (String username,String password) {
			built = new UserDTO();
			built.userName = username;
			built.password = password;
		}

		public UserDTO build() {
			return built;
		}

		public Builder email(String email) {
			built.email = email;
			return this;
		}
		
		public Builder firstName(String firstName) {
			built.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName) {
			built.lastName = lastName;
			return this;
		}
		
		public Builder roles(Set<Role> roles) {
			built.roles = roles;
			return this;
		}


	}


	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + "]";
	}


	

}
