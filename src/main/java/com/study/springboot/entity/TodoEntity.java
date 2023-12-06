package com.study.springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="todo")
@Entity
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq")
	@SequenceGenerator(name="todo_seq", sequenceName = "todo_SEQ", allocationSize = 1)
	private Long id;
	private String userId;
	private String title;
	private boolean done;
	
}
