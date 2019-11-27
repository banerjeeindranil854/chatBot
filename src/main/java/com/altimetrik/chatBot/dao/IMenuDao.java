package com.altimetrik.chatBot.dao;

import java.util.List;

import com.altimetrik.chatBot.entities.Menu;

public interface IMenuDao {
	
	List<Menu> getMenu(String menuName);
	
	String getMostTraversedPath();

}