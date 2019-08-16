package com.spring.mvc.test.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.model.Todo.Builder;

public class TodoDTO {

	private Long id;

	@Length(max = Todo.MAX_LENGTH_DESCRIPTION)
	private String description;

	@NotEmpty
	@Length(max = Todo.MAX_LENGTH_TITLE)
	private String title;

	public TodoDTO() {

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
		private TodoDTO built;

		public Builder (String title) {
			built = new TodoDTO();
			built.title = title;
		}

		public TodoDTO build() {
			return built;
		}

		public Builder description(String desc) {
			built.description = desc;
			return this;
		}

	}


	@Override
	public String toString() {
		return "TodoDTO [id=" + id + ", description=" + description + ", title=" + title + "]";
	}

}
