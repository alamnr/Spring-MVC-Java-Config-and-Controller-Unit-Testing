package com.spring.mvc.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.mvc.test.model.Todo;

//@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	public List<Todo> findByTitle(String  title);
	
}
