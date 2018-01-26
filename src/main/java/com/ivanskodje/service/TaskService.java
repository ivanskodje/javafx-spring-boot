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
	public void addTask(Task task) {
		try {
			taskRepository.save(task);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public Task getTask(Long id) {
		try {
			return taskRepository.findOne(id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Transactional(readOnly = true)
	public void printAll() {
		try {
			taskRepository.findAll().forEach(System.out::println);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
