package com.altimetrik.chatBot.service;

import java.util.List;

import com.altimetrik.chatBot.model.Menu;

public interface ChatBotServiceInterface {
public List<Menu> getAllMenu(Menu menu);

public List<Menu> getMaxTraversal();
}
