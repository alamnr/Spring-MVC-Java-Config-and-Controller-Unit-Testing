package com.spring.mvc.test.controllers;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.test.model.Todo;
import com.spring.mvc.test.service.TodoService;

@Controller

public class TodoController {
	
	private final TodoService service;
	
	private final MessageSource messageSource;

	@Autowired
	public TodoController(TodoService service, MessageSource messageSource) {
		super();
		this.service = service;
		this.messageSource = messageSource;
	}
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String findAllTodo(Model model)
	{
		model.addAttribute("todoList",service.findAll());
		return "todo/todo_list";
	}
	
	
	@RequestMapping(value="/todo/{todoId}")
	public String findById(@PathVariable("todoId") Long todoId, Model model)
	{
		model.addAttribute("todo",service.findById(todoId));
		return "todo/todo_details";
	}
	
	@RequestMapping(value="/todo/add", method= RequestMethod.GET)
	public String addNewTodo(Model model)
	{
		return "todo/todo_add";
	}
	
	@RequestMapping(value="/todo/save", method=RequestMethod.POST)
	public String saveTodo( @ModelAttribute("todo") @Valid Todo todo,Errors errors ,Model model)
	{
		
		System.out.println(todo);
		if(!errors.hasErrors()) {
			System.out.println("The Todo validated");
		}
		if(errors.hasErrors()) {
			return "todo/todo_add";
		}
		
		this.service.addTodo(todo);
		System.out.println("Saving todo");
		return "redirect:/";
	}
	
	@ModelAttribute("todo")
	public Todo getTodo() {
		return new Todo();
	}
	

}
