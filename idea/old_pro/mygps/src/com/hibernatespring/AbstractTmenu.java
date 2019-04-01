package com.hibernatespring;

/**
 * AbstractTmenu entity provides the base persistence definition of the Tmenu
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTmenu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private String menuType;
	private String menuKeyOrUrl;
	private Integer fatherMenuId;

	// Constructors

	/** default constructor */
	public AbstractTmenu() {
	}

	/** full constructor */
	public AbstractTmenu(String menuName, String menuType, String menuKeyOrUrl,
			Integer fatherMenuId) {
		this.menuName = menuName;
		this.menuType = menuType;
		this.menuKeyOrUrl = menuKeyOrUrl;
		this.fatherMenuId = fatherMenuId;
	}

	// Property accessors

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public String getMenuKeyOrUrl() {
		return this.menuKeyOrUrl;
	}

	public void setMenuKeyOrUrl(String menuKeyOrUrl) {
		this.menuKeyOrUrl = menuKeyOrUrl;
	}

	public Integer getFatherMenuId() {
		return this.fatherMenuId;
	}

	public void setFatherMenuId(Integer fatherMenuId) {
		this.fatherMenuId = fatherMenuId;
	}

}