package com.altimetrik.chatBot.reposortory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.altimetrik.chatBot.entities.Menu;
@Repository
public interface IMenuDaoReposortory extends JpaRepository<Menu, Integer>{
	
	
	
	@Query(value = "select * from chatbot.menu where type='root'", 
			  nativeQuery = true)
	List<Menu> getRootMenu();
	@Query(value = "select a.* from chatbot.menu a,chatbot.menu_relation b where \r\n" + 
			"b.parent_id=?1\r\n" + 
			"and a.menu_id=b.child_id\r\n" + 
			"and a.type <> 'Root'", 
			  nativeQuery = true)
	List<Menu> getMenu(Integer id);
	@Query(value = "select * from chatbot.menu b where b.counter= (select max(a.counter) from chatbot.menu a where\r\n" + 
			"a.type = 'Question')\r\n" + 
			"and b.type='Question'\r\n" + 
			"and b.counter<>0", 
			  nativeQuery = true)
	List<Menu> maxCounterNodes();
	@Query(value = "select a.* from chatbot.menu a,chatbot.menu_relation b where b.child_id=?1\r\n" + 
			"and a.menu_id=b.parent_id", 
			  nativeQuery = true)
	Menu getParent(Integer id);
}
