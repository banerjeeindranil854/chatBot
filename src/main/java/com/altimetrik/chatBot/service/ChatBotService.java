package com.altimetrik.chatBot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.chatBot.dao.IMenuDao;
import com.altimetrik.chatBot.entities.Menu;

@Service
public class ChatBotService implements ChatBotServiceInterface{

	@Autowired
	IMenuDao iMenuDao;
	@Override
	public List<Menu> getAllMenu(Menu menu) {
		
		return iMenuDao.getMenu(menu);
	}

	@Override
	public List<Menu> getMaxTraversal() {
		return iMenuDao.getMostTraversedPath();
	}

}
