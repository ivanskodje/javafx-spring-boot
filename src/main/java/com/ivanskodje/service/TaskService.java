package com.ivanskodje.service;

import com.ivanskodje.domain.Task;
import com.ivanskodje.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService
{
	@Autowired
	TaskRepository taskRepository;

	@Transactional
	public void addTask(Task task)
	{
		taskRepository.save(task);
	}

	@Transactional(readOnly = true)
	public Task getTask(Long id)
	{
		return taskRepository.findOne(id);
	}

	public void printAll()
	{
		taskRepository.findAll().forEach(System.out::println);
	}
}
