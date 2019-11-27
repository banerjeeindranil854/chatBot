package com.altimetrik.chatBot.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.altimetrik.chatBot.entities.Menu;

import antlr.StringUtils;

@Repository
public class MenuDaoImpl implements IMenuDao {
	private static final Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Menu> getMenu(Menu menu) {


		if(menu.getMenuName().equals("")) {
		Query query=entityManager.createQuery("select FROM Menu where type = 'Root'",Menu.class);
		return query.getResultList();
		}
		else {
			menu.setCount(menu.getCount()+1);
			entityManager.persist(menu);
			return menu.getChilds();
		}
	}

	public List<Menu> getMostTraversedPath() {
		
		Query query=entityManager.createNativeQuery("SELECT * FROM menu" + 
				"		WHERE counter = (SELECT Max(counter) FROM menu)", Menu.class);

		ArrayList<Menu> mostTraversedMenus = new ArrayList<>();
		Menu menu = (Menu) query.getSingleResult();
		mostTraversedMenus.add(menu);
		Menu parentMenu = menu.getParent();
		
		if(parentMenu != null)
		{
			mostTraversedMenus.add(parentMenu);
			if(parentMenu.getParent() != null)
			{
				mostTraversedMenus.add(parentMenu.getParent());
			}
		}
		
		return mostTraversedMenus;
	}

	public void updateCount(Menu menu) {
		menu.setCount(menu.getCount()+1);
		entityManager.persist(menu);
	}
}
