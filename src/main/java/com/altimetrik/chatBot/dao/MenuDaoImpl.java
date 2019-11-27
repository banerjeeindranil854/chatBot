package com.altimetrik.chatBot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.altimetrik.chatBot.entities.Menu;
import com.altimetrik.chatBot.reposortory.IMenuDaoReposortory;

@Component
public class MenuDaoImpl {
	private static final Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	IMenuDaoReposortory iMenuDaoReposortory;
	
	public List<Menu> getMenu(Menu menu) {


		if(menu.getMenuName().equals("")) {
			return iMenuDaoReposortory.getRootMenu();
		}
		else {
			updateMenu(menu);
			return iMenuDaoReposortory.getMenu(menu.getId());
		}
	}
	
	public void updateMenu(Menu menu) {
		menu.setCount(menu.getCount()+1);
		iMenuDaoReposortory.save(menu);
	}

	public List<Menu> getMostTraversedPath(List<Menu> allMenuItem) {
		for(Menu menu:iMenuDaoReposortory.maxCounterNodes()) {
			allMenuItem.add(menu);
			getParentAll(allMenuItem,menu);
		}
		return	allMenuItem;
		
	}
	public void getParentAll(List<Menu> allMenuItem,Menu childId){
		if(childId.getType().equals("Root")) {
			return;
		}
		allMenuItem.add(iMenuDaoReposortory.getParent(childId.getId()));
		
		getParentAll(allMenuItem,iMenuDaoReposortory.getParent(childId.getId()));
	}
}
