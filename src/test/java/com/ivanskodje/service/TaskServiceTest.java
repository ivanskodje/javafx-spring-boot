package com.ivanskodje.service;

import com.ivanskodje.Main;
import com.ivanskodje.domain.Task;
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
import org.springframework.transaction.TransactionSystemException;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@ActiveProfiles("test")
public class TaskServiceTest {
	@Autowired
	private TaskService taskService;

	@Before
	public void setup() {
		taskService.printAll();
	}

	@After
	public void tearDown() {
		taskService.deleteAll();
	}

	@Test
	public void addNewItem() {
		String expectedName = "TASKINATOR";
		String expectedDescription = "Hail and Kill";
		Task receivedItem = addTask(buildItem(expectedName, expectedDescription));
		Assert.assertNotNull(receivedItem.getId());
		Assert.assertEquals(expectedName, receivedItem.getName());
		Assert.assertEquals(expectedDescription, receivedItem.getDescription());
	}

	@Test
	public void addNewItemAndGetItem() {
		String name = "Task Name";
		String description = "My Task Description";
		Task task = addTask(buildItem(name, description));
		Task receivedTask = getItem(task.getId());
		assertEqualItems(task, receivedTask);
	}

	@Test(expected = DataIntegrityViolationException.class)
	public void addItem_failed_nullItemData_ThrowsTransactionSystemException() {
		taskService.addTask(new Task(null));
	}

	@Test
	public void printAll() {
		taskService.printAll();
	}

	private Task getItem(Long id) {
		return taskService.getTask(id);
	}

	private void assertEqualItems(Task item, Task receivedItem) {
		Assert.assertNotNull(item);
		Assert.assertNotNull(receivedItem);
		Assert.assertEquals(item.getId(), receivedItem.getId());
		Assert.assertEquals(item.getName(), receivedItem.getName());
		Assert.assertEquals(item.getDescription(), receivedItem.getDescription());
	}

	private Task addTask(Task task) {
		return taskService.addTask(task);
	}

	private Task buildItem(String expectedName, String expectedDescription) {
		Task task = new Task(expectedName);
		task.setDescription(expectedDescription);
		return task;
	}
}