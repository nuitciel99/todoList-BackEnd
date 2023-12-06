package com.study.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.dto.ResponseDTO;
import com.study.springboot.dto.TodoDto;
import com.study.springboot.entity.TodoEntity;
import com.study.springboot.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping
	public ResponseEntity<?> retrieveTodoList(){
		String temporaryUserId = "temporaryUser";
		List<TodoEntity> entities = todoService.retrieve(temporaryUserId);
		List<TodoDto> dtos = entities.stream()
				.map(entity -> new TodoDto(entity)).collect(Collectors.toList());
		ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDto dto){
		try {
			String temporaryUserId = "temporaryUser";
			TodoEntity entity = TodoDto.toEntity(dto);
			entity.setId(null);
			entity.setUserId(temporaryUserId);
			List<TodoEntity> entities = todoService.create(entity);
			List<TodoDto> dtos = entities.stream()
					.map(ent -> new TodoDto(ent)).collect(Collectors.toList());
			ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDto dto){
		String temporaryUserId = "temporaryUser";
		TodoEntity entity = TodoDto.toEntity(dto);
		entity.setUserId(temporaryUserId);
		List<TodoEntity> entities = todoService.update(entity);
		List<TodoDto> dtos = entities.stream()
				.map(TodoDto::new).collect(Collectors.toList());
		ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDto dto){
		try {
			String temporaryUserId = "temporaryUser";
			TodoEntity entity = TodoDto.toEntity(dto);
			entity.setUserId(temporaryUserId);
			List<TodoEntity> entities = todoService.delete(entity);
			List<TodoDto> dtos = entities.stream()
					.map(TodoDto::new).collect(Collectors.toList());
			ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().data(dtos).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDto> response = ResponseDTO.<TodoDto>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
