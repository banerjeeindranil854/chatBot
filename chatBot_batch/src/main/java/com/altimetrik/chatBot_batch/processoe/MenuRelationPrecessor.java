package com.altimetrik.chatBot_batch.processoe;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.altimetrik.chatBot_batch.model.MenuRelation;

public class MenuRelationPrecessor implements ItemProcessor<MenuRelation, MenuRelation> {

	@Override
	public MenuRelation process(final MenuRelation menuRelation) throws Exception {

		final BigDecimal parentNode = menuRelation.getParentNode();
		final BigDecimal childNode = menuRelation.getChildNode();
		final int id = menuRelation.getId();
		final MenuRelation transformedMenuRelation = new MenuRelation(id, parentNode, childNode);

		return transformedMenuRelation;
	}

}
