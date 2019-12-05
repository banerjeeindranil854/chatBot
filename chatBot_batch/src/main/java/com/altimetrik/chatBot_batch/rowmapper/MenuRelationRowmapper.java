package com.altimetrik.chatBot_batch.rowmapper;

import java.math.BigDecimal;

import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import com.altimetrik.chatBot_batch.model.MenuRelation;

public class MenuRelationRowmapper implements RowMapper<MenuRelation>{

	
	@Override
	public MenuRelation mapRow(RowSet rs) throws Exception {
		if (rs == null || rs.getCurrentRow() == null) {
            return null;
        }
		MenuRelation menuRelation=new MenuRelation();
		menuRelation.setParentNode(BigDecimal.valueOf(Double.parseDouble(rs.getColumnValue(0))));
		menuRelation.setChildNode((BigDecimal.valueOf(Double.parseDouble(rs.getColumnValue(1)))));
		menuRelation.setId((int)Double.parseDouble(rs.getColumnValue(2)));
		return menuRelation;
	}
	

}
