package com.altimetrik.chatBot_batch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Menu
 */
@Entity

@Table(name="menu")
public class Menu   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="menu_id")
	@JsonProperty(value = "id")
	private Integer id = null;

	
	@Column(name="menu_name")
	@JsonProperty(value = "menuName")
	private String menuName = null;

	@Column(name="counter")
	@JsonProperty(value = "count")
	private Integer count = null;

	
	@Column(name="type")
	private String type = null;

	@Column(name="description")
	@JsonProperty(value = "description")
	private String description = null;
	public Menu() {
		super();
	}
	public Menu(Integer id, String menuName, Integer count, String type, String description) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.count = count;
		this.type = type;
		this.description = description;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
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
