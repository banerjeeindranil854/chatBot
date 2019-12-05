package com.altimetrik.chatBot.entity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.altimetrik.chatBot.entities.Menu;

@RunWith(MockitoJUnitRunner.class)
public class MenuTest {

	@Test
	public void assertionTest() {
	Menu menu=new Menu();
	menu.setId(1);
	menu.setMenuName("TEST_NAME");
	menu.setDescription("TEST_DESCRIPTION");
	menu.setCount(0);
	menu.setType("QUESTION_TEST");
	Assert.assertEquals(menu.getDescription(), "TEST_DESCRIPTION");
	Assert.assertEquals(menu.getMenuName(), "TEST_NAME");
	Assert.assertEquals(menu.getType(), "QUESTION_TEST");
	}
}
