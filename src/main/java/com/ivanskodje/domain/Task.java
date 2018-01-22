package com.ivanskodje.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "task")
@EqualsAndHashCode(exclude = {"id"})
public @Data class Task
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description")
	private String description;

	@SuppressWarnings("unused")
	public Task()
	{
	}

	public Task(String name)
	{
		this.name = name;
	}
}
