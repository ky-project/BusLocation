package com.hibernatespring;

import java.util.Date;

/**
 * AbstractNews entity provides the base persistence definition of the News
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractNews implements java.io.Serializable {

	// Fields

	private Integer newId;
	private String newTitle;
	private String newContent;
	private Date faTime;
	private String faPeople;

	// Constructors

	/** default constructor */
	public AbstractNews() {
	}

	/** full constructor */
	public AbstractNews(String newTitle, String newContent, Date faTime,
			String faPeople) {
		this.newTitle = newTitle;
		this.newContent = newContent;
		this.faTime = faTime;
		this.faPeople = faPeople;
	}

	// Property accessors

	public Integer getNewId() {
		return this.newId;
	}

	public void setNewId(Integer newId) {
		this.newId = newId;
	}

	public String getNewTitle() {
		return this.newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getNewContent() {
		return this.newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public Date getFaTime() {
		return this.faTime;
	}

	public void setFaTime(Date faTime) {
		this.faTime = faTime;
	}

	public String getFaPeople() {
		return this.faPeople;
	}

	public void setFaPeople(String faPeople) {
		this.faPeople = faPeople;
	}

}