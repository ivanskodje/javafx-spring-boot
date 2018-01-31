package com.ivanskodje.service;

import com.ivanskodje.domain.Item;
import com.ivanskodje.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
	private ItemRepository itemRepository;

	@Autowired
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Transactional
	public Item addItem(Item item) {
		return itemRepository.save(item);
	}

	@Transactional(readOnly = true)
	public Item getItem(Long id) {
		return itemRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public void printAll() {
		itemRepository.findAll().forEach(System.out::println);
	}

	@Transactional
	public void deleteItem(Long id) {
		itemRepository.delete(id);
	}

	@Transactional
	public void deleteAll() {
		itemRepository.deleteAll();
	}
}