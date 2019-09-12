package com.spring.mvc.test.security.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.mvc.test.dto.UserDTO;
import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.model.Todo.Builder;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class AppUser implements UserDetails 
{
	public static final int MAX_LENGTH_EMAIL = 50;
	public static final int MAX_LENGTH_USERNAME = 50;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<Role> roles;

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

		return this.userName;
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

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + ",  createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}

	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date lastModifiedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public static Builder getBuilder(String username,String password) {
		return new Builder(username,password);
	}

		
	public static class Builder {
		private AppUser built;

		public Builder (String username,String password) {
			built = new AppUser();
			built.userName = username;
			built.password = password;
		}

		public AppUser build() {
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
	
	 public void update(String password, String firstName, String lastName, String email ) {
	        this.password = password;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return AuthorityUtils.commaSeparatedStringToAuthorityList(
				this.getRoles().stream().map(obj -> obj.getRoleName()).collect(Collectors.joining(",")));
	}

	@Override
	public String getUsername() {

		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

}
