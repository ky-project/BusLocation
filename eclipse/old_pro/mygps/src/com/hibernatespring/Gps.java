package com.hibernatespring;

import java.util.Date;

/**
 * Gps entity. @author MyEclipse Persistence Tools
 */
public class Gps extends AbstractGps implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Gps() {
	}

	/** full constructor */
	public Gps(Integer busId, float busLati, float busLongi, Date busTime,
			String status, String latiSphere, String longiSphere, float speed,
			Date date, float directon) {
		super(busId, busLati, busLongi, busTime, status, latiSphere,
				longiSphere, speed, date, directon);
	}

}
