package com.spring.mvc.test.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Todo {

	public static final int MAX_LENGTH_DESCRIPTION = 100;
	public static final int MAX_LENGTH_TITLE = 300;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Length(max = MAX_LENGTH_DESCRIPTION, message="Description is too long")
	private String description;

	@NotEmpty(message="Title should not be empty")
	@Length(max = MAX_LENGTH_TITLE, message="Title is too long")
	private String title;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedBy
	private String lastModifiedBy;
	
	@CreatedDate
	private Date createdDate;
	
	@LastModifiedDate
	private Date lastModifiedDate;

	public Todo() {

	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Builder getBuilder(String title) {
		return new Builder(title);
	}


	public static class Builder {
		private Todo built;

		public Builder(String title) {
			built = new Todo();
			built.title = title;
		}

		public Todo build() {
			return built;
		}

		public Builder description(String desc) {
			built.description = desc;
			return this;
		}

	}
	
	 public void update(String description, String title) {
	        this.description = description;
	        this.title = title;
	    }


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


	@Override
	public String toString() {
		return "Todo [id=" + id + ", description=" + description + ", title=" + title + ", createdBy=" + createdBy
				+ ", lastModifiedBy=" + lastModifiedBy + ", createdDate=" + createdDate + ", lastModifiedDate="
				+ lastModifiedDate + "]";
	}

	
}
