package com.spring.mvc.test.model;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Todo {

	public static final int MAX_LENGTH_DESCRIPTION = 10;
	public static final int MAX_LENGTH_TITLE = 10;

	private Long id;

	private Date creationTime;

	private Date modificationTime;

	@Length(max = MAX_LENGTH_DESCRIPTION, message="Description is too long")
	private String description;

	@NotEmpty(message="Title should not be empty")
	@Length(max = MAX_LENGTH_TITLE, message="Title is too long")
	private String title;

	public Todo() {

	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
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
	
	

	@Override
	public String toString() {
		return "Todo [id=" + id + ", creationTime=" + creationTime + ", modificationTime=" + modificationTime
				+ ", description=" + description + ", title=" + title + "]";
	}

}
