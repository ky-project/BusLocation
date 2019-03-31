package com.hibernatespring;

import java.util.Date;

/**
 * Regubus entity. @author MyEclipse Persistence Tools
 */
public class Regubus extends AbstractRegubus implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Regubus() {
	}

	/** minimal constructor */
	public Regubus(Integer regId) {
		super(regId);
	}

	/** full constructor */
	public Regubus(Integer regId, String regStart, String regEnd,
			String regContent, Date regStartTime, Integer busId, String regNote) {
		super(regId, regStart, regEnd, regContent, regStartTime, busId, regNote);
	}

}
