package com.ivanskodje.service;

import com.ivanskodje.domain.Item;
import com.ivanskodje.repository.ItemRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest
{
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemService itemService;


	@Test
	public void addItemSuccessfully()
	{
		Long id = itemService.addItem(new Item("Item"));
		Long expectedId = 1L;
		Assert.assertEquals(expectedId, id);

		Long id2 = itemService.addItem(new Item("Item 2"));
		expectedId = 2L;
		Assert.assertEquals(expectedId, id2);

		Long id3 = itemService.addItem(new Item("Item 3"));
		expectedId = 3L;
		Assert.assertEquals(expectedId, id3);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void addItemFailedNoNullAllowed()
	{
		itemService.addItem(new Item(null));
	}

	@Test
	public void getItem()
	{
		itemService.addItem(new Item("Item"));
		Long expectedId = 1L;

		Item receivedItem = itemService.getItem(1L);
		Assert.assertEquals(receivedItem.getId(), expectedId);
	}

	@Test
	public void printAll()
	{
		itemService.printAll();
	}
}