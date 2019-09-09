package com.spring.mvc.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;


import com.spring.mvc.test.model.Todo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"classpath:testPersistenceContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
	DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class/*,
    DbUnitTestExecutionListener.class*/})
public class TodoRepositoryTest {

	@Autowired
	private TodoRepository todoRepository;
	
	@Test
	public void findTodoByTitle() {
		Todo todo = new Todo();
		todo.setTitle("Learn Spring");
		todo.setDescription("Desc Learn Spring");
		todoRepository.save(todo);
		
		List<Todo> todos = todoRepository.findByTitle("Learn Spring");
		
		assertEquals(1, todos.size());
		
		assertThat(todos).extracting(Todo::getTitle).containsOnly("Learn Spring");
		
	}
}
