package com.hibernatespring;

import java.util.Date;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
public class News extends AbstractNews implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public News() {
	}

	/** full constructor */
	public News(String newTitle, String newContent, Date faTime, String faPeople) {
		super(newTitle, newContent, faTime, faPeople);
	}

}
