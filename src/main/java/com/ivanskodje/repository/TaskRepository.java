package com.ivanskodje.repository;

import com.ivanskodje.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
	List<Task> findByName(String name);
}
