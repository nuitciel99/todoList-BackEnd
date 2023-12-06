package com.study.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.entity.TodoEntity;
import com.study.springboot.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	public List<TodoEntity> create(TodoEntity entity){
		validate(entity);
		todoRepository.save(entity);
		return todoRepository.findByUserId(entity.getUserId());
	}

	private void validate(TodoEntity entity) {
		
		if(entity == null) {
			throw new RuntimeException("entity cannot be null");
		}
		if(entity.getUserId() == null) {
			throw new RuntimeException("unknown UserId");
		}
		
	}
	
	public List<TodoEntity> retrieve(String userId){
		return todoRepository.findByUserId(userId);
	}

	public List<TodoEntity> update(TodoEntity entity) {
		validate(entity);
		final Optional<TodoEntity> original = todoRepository.findById(entity.getId());
		original.ifPresent(todo -> {
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			todoRepository.save(todo);
		});
		return retrieve(entity.getUserId());
	}
	
	public List<TodoEntity> delete(TodoEntity entity){
		validate(entity);
		try {
			todoRepository.delete(entity);
		} catch (Exception e) {
			throw new RuntimeException("error deleting entity" + entity.getId());
		}
		return retrieve(entity.getUserId());
	}
	
}
