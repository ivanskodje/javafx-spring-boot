package com.ivanskodje.repository;

import com.ivanskodje.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	List<Item> findByName(String name);

	List<Item> findByValue(Long value);
}
