package com.spring.mvc.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.mvc.test.model.Todo;

@Service
public class TodoService {
	
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
	
	public Todo addTodo(Todo todo) {
		todo.setId((long) todos.size()+1);
		todos.add(todo);
		return todo;
		
	}
}
