package com.ivanskodje.service;

import com.ivanskodje.domain.Item;
import com.ivanskodje.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
	private ItemRepository itemRepository;

	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Transactional
	public Item addItem(Item item) {
		try {
			return (item != null) ? itemRepository.save(item) : null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Item getItem(Long id) {
		try {
			return itemRepository.findOne(id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Transactional(readOnly = true)
	public void printAll() {
		try {
			itemRepository.findAll().forEach(System.out::println);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}