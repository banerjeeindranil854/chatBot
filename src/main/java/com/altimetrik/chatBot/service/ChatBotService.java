package com.altimetrik.chatBot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.chatBot.dao.IMenuDao;
import com.altimetrik.chatBot.entities.Menu;
import com.altimetrik.chatBot.model.MenuVo;

@Service
public class ChatBotService implements ChatBotServiceInterface{

	@Autowired
	IMenuDao iMenuDao;
	@Override
	public List<MenuVo> getAllMenu(MenuVo menu) {
		List<MenuVo> MenuVos=new ArrayList<MenuVo>();
		Menu Menu=new Menu(menu);
		for(Menu menuInd : iMenuDao.getMenu(Menu)) {
			MenuVos.add(new MenuVo(menuInd));
		}
		return MenuVos;
	}

	@Override
	public List<MenuVo> getMaxTraversal() {
		List<MenuVo> MenuVos=new ArrayList<MenuVo>();
		for(Menu menuInd : iMenuDao.getMostTraversedPath()) {
			MenuVos.add(new MenuVo(menuInd));
		}
		return MenuVos;
	}

}
