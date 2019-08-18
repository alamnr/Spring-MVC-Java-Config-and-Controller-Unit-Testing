package com.spring.mvc.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spring.mvc.test.dto.TodoDTO;
import com.spring.mvc.test.model.Todo;

import com.spring.mvc.test.exception.TodoNotFoundException;


@Service
public class TodoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);
	
	private static List<Todo>  todos = new ArrayList<Todo>();
	
	public TodoService()	
	{
		System.out.println("Initialized");
		Todo obj1 = Todo.getBuilder("Spring MVC maven project config").description("Building Spring mvc project").build();
		obj1.setId(1L);
		Todo obj2 = Todo.getBuilder("Adding dispatcher servlet and web app  context and  application context").description("webapp and app context setup").build();
		obj2.setId(2L);
		todos.addAll(Arrays.asList(obj1,obj2));
	}
	
	public List<Todo>  findAll()
	{
		
		return todos;
	}
	
	public Todo findById(Long id) {
		
		Todo todo = todos.stream()
				.filter(obj->obj.getId().equals(id))
				.findAny()
				.orElse(null);
		
		return  todo;
		
	}
	
	public Todo addTodo(TodoDTO todoDTO) {
		LOGGER.debug("Adding a new to-do entry with information: {}", todoDTO);

        Todo model = Todo.getBuilder(todoDTO.getTitle())
                .description(todoDTO.getDescription())
                .build();
		model.setId((long) todos.size()+1);
		todos.add(model);
		return model;
		
	}

	public Todo deleteById(Long id) {
		LOGGER.debug("Deleting a to-do entry with id: {}", id);

        Todo deleted = findById(id);
        LOGGER.debug("Deleting to-do entry: {}", deleted);

        todos.remove(deleted);
        return deleted;
	}

	public Todo update(TodoDTO updated) throws TodoNotFoundException {
		LOGGER.debug("Updating contact with information: {}", updated);

        Todo model = findById(updated.getId());
        LOGGER.debug("Found a to-do entry: {}", model);

        model.update(updated.getDescription(), updated.getTitle());

        return model;

	}
}
