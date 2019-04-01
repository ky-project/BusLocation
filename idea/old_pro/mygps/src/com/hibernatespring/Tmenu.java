package com.hibernatespring;

/**
 * Tmenu entity. @author MyEclipse Persistence Tools
 */
public class Tmenu extends AbstractTmenu implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Tmenu() {
	}

	/** full constructor */
	public Tmenu(String menuName, String menuType, String menuKeyOrUrl,
			Integer fatherMenuId) {
		super(menuName, menuType, menuKeyOrUrl, fatherMenuId);
	}

}
