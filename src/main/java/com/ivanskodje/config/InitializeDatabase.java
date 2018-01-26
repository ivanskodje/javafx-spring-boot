package com.ivanskodje.config;

import com.ivanskodje.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabase implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	ItemService itemService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
//		System.out.println("--- BEFORE ADDING ITEMS --- ");
//		itemService.printAll();
//
//		Item itemSamuraiSword = new Item("Samurai Sword " + System.currentTimeMillis());
//		itemSamuraiSword.setValue(66800L);
//		itemService.addItem(itemSamuraiSword);
//
//		Item itemTank = new Item("M4 Sherman " + System.currentTimeMillis());
//		itemTank.setValue(33500L);
//		itemService.addItem(itemTank);
//
//		System.out.println("--- AFTER ADDING ITEMS --- ");
//		itemService.printAll();
	}
}