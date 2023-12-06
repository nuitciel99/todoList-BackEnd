package com.study.springboot.dto;

import com.study.springboot.entity.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
	
	private Long id;
//	private String userId;
	private String title;
	private boolean done;
	
	public TodoDto(TodoEntity entity) {
		this.id = entity.getId();
//		this.userId = entity.getUserId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
	}

	public static TodoEntity toEntity(TodoDto dto) {

		return TodoEntity.builder()
				.id(dto.getId())
//				.userId(dto.getUserId())
				.title(dto.getTitle())
				.done(dto.isDone())
				.build();
	}

}
