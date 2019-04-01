package com.hibernatespring;

import java.util.Date;

/**
 * AbstractRegubus entity provides the base persistence definition of the
 * Regubus entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractRegubus implements java.io.Serializable {

	// Fields

	private Integer regId;
	private String regStart;
	private String regEnd;
	private String regContent;
	private Date regStartTime;
	private Integer busId;
	private String regNote;

	// Constructors

	/** default constructor */
	public AbstractRegubus() {
	}

	/** minimal constructor */
	public AbstractRegubus(Integer regId) {
		this.regId = regId;
	}

	/** full constructor */
	public AbstractRegubus(Integer regId, String regStart, String regEnd,
			String regContent, Date regStartTime, Integer busId, String regNote) {
		this.regId = regId;
		this.regStart = regStart;
		this.regEnd = regEnd;
		this.regContent = regContent;
		this.regStartTime = regStartTime;
		this.busId = busId;
		this.regNote = regNote;
	}

	// Property accessors

	public Integer getRegId() {
		return this.regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getRegStart() {
		return this.regStart;
	}

	public void setRegStart(String regStart) {
		this.regStart = regStart;
	}

	public String getRegEnd() {
		return this.regEnd;
	}

	public void setRegEnd(String regEnd) {
		this.regEnd = regEnd;
	}

	public String getRegContent() {
		return this.regContent;
	}

	public void setRegContent(String regContent) {
		this.regContent = regContent;
	}

	public Date getRegStartTime() {
		return this.regStartTime;
	}

	public void setRegStartTime(Date regStartTime) {
		this.regStartTime = regStartTime;
	}

	public Integer getBusId() {
		return this.busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getRegNote() {
		return this.regNote;
	}

	public void setRegNote(String regNote) {
		this.regNote = regNote;
	}

}