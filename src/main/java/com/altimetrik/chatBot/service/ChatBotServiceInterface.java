package com.altimetrik.chatBot.service;

import java.util.List;

import com.altimetrik.chatBot.entities.Menu;

public interface ChatBotServiceInterface {
public List<Menu> getAllMenu(Menu menu);

public List<Menu> getMaxTraversal();
}
