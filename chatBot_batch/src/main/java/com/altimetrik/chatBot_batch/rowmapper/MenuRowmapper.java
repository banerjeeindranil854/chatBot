package com.altimetrik.chatBot_batch.rowmapper;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.altimetrik.chatBot_batch.model.Menu;

public class MenuRowmapper implements RowMapper<Menu>{

	
	@Override
	public Menu mapRow(RowSet rs) throws Exception {
		if (rs == null || rs.getCurrentRow() == null) {
            return null;
        }
		Menu menu=new Menu();
		menu.setId((int)Double.parseDouble(rs.getColumnValue(0)));
		menu.setMenuName(rs.getColumnValue(1));
		menu.setCount((int)Double.parseDouble(rs.getColumnValue(2)));
		menu.setType(rs.getColumnValue(3));
		menu.setDescription(rs.getColumnValue(4));
		
	return menu;
	}

}
