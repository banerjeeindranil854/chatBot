package com.altimetrik.chatBot.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.altimetrik.chatBot.model.MenuVo;

/**
 * Menu
 */
@Entity
@Table(name = "menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "menu_id")
	private Integer id = null;

	@Column(name = "menu_name")
	private String menuName = null;

	@Column(name = "counter")
	private String count = null;

	@Column(name = "type")
	private String type = null;

	@Column(name = "description")
	private String description = null;

	public Menu(Menu menu) {

	}

	public Menu(MenuVo menuVo) {
    this.id=menuVo.getId();
    this.menuName=menuVo.getName();
    this.count=menuVo.getCount();
    this.type=menuVo.getType();
    this.description=menuVo.getDescription();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
