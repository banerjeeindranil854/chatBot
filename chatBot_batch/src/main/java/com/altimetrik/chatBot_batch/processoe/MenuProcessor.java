package com.altimetrik.chatBot_batch.processoe;

import org.springframework.batch.item.ItemProcessor;

import com.altimetrik.chatBot_batch.model.Menu;



public class MenuProcessor implements ItemProcessor<Menu, Menu> {

	@Override
	public Menu process(final Menu menu) throws Exception {

		final int menuId = menu.getId();
		final String menuName = menu.getMenuName();
		final String menuType = menu.getType();
		final int count = menu.getCount();
		final String menuDescription = menu.getDescription();
		final Menu transformedMenu = new Menu(menuId, menuName, count, menuType, menuDescription);

		return transformedMenu;
	}

}
