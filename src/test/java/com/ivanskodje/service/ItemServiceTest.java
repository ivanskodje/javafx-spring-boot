package com.ivanskodje.service;

import com.ivanskodje.domain.Item;
import com.ivanskodje.repository.ItemRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest {
	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository itemRepository;

	private static Item[] ITEMS;
	private static int ITEM_COUNT = 10;
	private static String ITEM_NAME = "Item";
	private static Long VALUE_MULTIPLIER = 100L;

	@Before
	public void setup() {
		ITEMS = new Item[ITEM_COUNT];
		for (int i = 0; i < ITEM_COUNT; i++) {
			Item item = new Item(ITEM_NAME + i);
			item.setValue(VALUE_MULTIPLIER * i);
			ITEMS[i] = item;
			itemRepository.save(item);
		}
	}

	@After
	public void tearDown() {
		for (int i = 0; i < ITEM_COUNT; i++) {
			itemRepository.delete(ITEMS[i]);
		}
	}

	@Test
	public void addItem_success_addingOneItem() {
		Long expectedId = 11L;
		String expectedName = "Terminator";
		Long expectedValue = 666L;

		Item item = insertItem(expectedName, expectedValue);

		verifyItem(expectedId, expectedName, expectedValue, item);
	}

	private void verifyItem(Long expectedId, String expectedName, Long expectedValue, Item item) {
		Assert.assertEquals(expectedId, item.getId());
		Assert.assertEquals(expectedName, item.getName());
		Assert.assertEquals(expectedValue, item.getValue());
	}

	private Item insertItem(String expectedName, Long expectedValue) {
		Item item = new Item(expectedName);
		item.setValue(expectedValue);
		item = itemService.addItem(item);
		return item;
	}

	@Test
	public void addItemFailedNoNullAllowed() {
		itemService.addItem(new Item(null));
	}

	@Test
	public void getItem() {
		itemService.addItem(new Item("Item"));
		Long expectedId = 1L;

		Item receivedItem = itemService.getItem(1L);
		Assert.assertEquals(receivedItem.getId(), expectedId);
	}

	@Test
	public void printAll() {
		itemService.printAll();
	}
}