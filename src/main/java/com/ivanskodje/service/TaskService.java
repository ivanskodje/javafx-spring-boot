package com.ivanskodje.service;

import com.ivanskodje.domain.Task;
import com.ivanskodje.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
	private TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Transactional
	public Task addTask(Task task) {
		return taskRepository.save(task);
	}

	@Transactional(readOnly = true)
	public Task getTask(Long id) {
		return taskRepository.findOne(id);
	}


	@Transactional
	public void deleteTask(Long id) {
		taskRepository.delete(id);
	}

	@Transactional
	public void deleteAll() {
		taskRepository.deleteAll();
	}

	@Transactional(readOnly = true)
	public void printAll() {
		taskRepository.findAll().forEach(System.out::println);
	}
}
