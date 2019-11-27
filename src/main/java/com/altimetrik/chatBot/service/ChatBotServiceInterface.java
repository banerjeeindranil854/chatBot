package com.altimetrik.chatBot.service;

import java.util.List;

import com.altimetrik.chatBot.model.MenuVo;

public interface ChatBotServiceInterface {
public List<MenuVo> getAllMenu(MenuVo menu);

public List<MenuVo> getMaxTraversal();
}
