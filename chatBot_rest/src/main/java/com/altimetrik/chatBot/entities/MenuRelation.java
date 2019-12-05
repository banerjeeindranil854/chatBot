package com.altimetrik.chatBot.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="menu_relation")
public class MenuRelation   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id = null;
	@Column(name="parent_id")
	private BigDecimal parentNode = null;

	@Column(name="child_id")
	private BigDecimal childNode = null;

	public MenuRelation() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getParentNode() {
		return parentNode;
	}

	public void setParentNode(BigDecimal parentNode) {
		this.parentNode = parentNode;
	}

	public BigDecimal getChildNode() {
		return childNode;
	}

	public void setChildNode(BigDecimal childNode) {
		this.childNode = childNode;
	}






}

