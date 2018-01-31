package com.ivanskodje.service;

import com.ivanskodje.Main;
import com.ivanskodje.domain.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("test")
public class ItemServiceTest {
	@Autowired
	private ItemService itemService;

	@Before
	public void setup() {
		itemService.printAll();
	}

	@After
	public void tearDown() {
		itemService.deleteAll();
	}

	@Test
	public void addNewItem() {
		String expectedName = "Terminator";
		Long expectedValue = 666L;
		Item receivedItem = addItem(buildItem(expectedName, expectedValue));
		Assert.assertNotNull(receivedItem.getId());
		Assert.assertEquals(expectedName, receivedItem.getName());
		Assert.assertEquals(expectedValue, receivedItem.getValue());
	}

	@Test
	public void addNewItemAndGetItem() {
		String name = "Item Name";
		Long value = 40L;
		Item item = addItem(buildItem(name, value));
		Item receivedItem = getItem(item.getId());
		assertEqualItems(item, receivedItem);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void addItem_failed_nullItemData_ThrowsTransactionSystemException() {
		itemService.addItem(new Item(null));
	}

	@Test
	public void printAll() {
		itemService.printAll();
	}

	private Item getItem(Long id) {
		return itemService.getItem(id);
	}

	private void assertEqualItems(Item item, Item receivedItem) {
		Assert.assertNotNull(item);
		Assert.assertNotNull(receivedItem);
		Assert.assertEquals(item.getId(), receivedItem.getId());
		Assert.assertEquals(item.getName(), receivedItem.getName());
		Assert.assertEquals(item.getValue(), receivedItem.getValue());
	}

	private Item addItem(Item item) {
		return itemService.addItem(item);
	}

	private Item buildItem(String expectedName, Long expectedValue) {
		Item item = new Item(expectedName);
		item.setValue(expectedValue);
		return item;
	}
}