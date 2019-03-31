package com.hibernatespring;

import java.util.Date;

/**
 * AbstractGps entity provides the base persistence definition of the Gps
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractGps implements java.io.Serializable {

	// Fields

	private Integer gpsId;
	private Integer busId;
	private float busLati;
	private float busLongi;
	private Date busTime;
	private String status;
	private String latiSphere;
	private String longiSphere;
	private float speed;
	private Date date;
	private float directon;

	// Constructors

	/** default constructor */
	public AbstractGps() {
	}

	/** full constructor */
	public AbstractGps(Integer busId, float busLati, float busLongi,
			Date busTime, String status, String latiSphere, String longiSphere,
			float speed, Date date, float directon) {
		this.busId = busId;
		this.busLati = busLati;
		this.busLongi = busLongi;
		this.busTime = busTime;
		this.status = status;
		this.latiSphere = latiSphere;
		this.longiSphere = longiSphere;
		this.speed = speed;
		this.date = date;
		this.directon = directon;
	}

	// Property accessors

	public Integer getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(Integer gpsId) {
		this.gpsId = gpsId;
	}

	public Integer getBusId() {
		return this.busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public float getBusLati() {
		return this.busLati;
	}

	public void setBusLati(float busLati) {
		this.busLati = busLati;
	}

	public float getBusLongi() {
		return this.busLongi;
	}

	public void setBusLongi(float busLongi) {
		this.busLongi = busLongi;
	}

	public Date getBusTime() {
		return this.busTime;
	}

	public void setBusTime(Date busTime) {
		this.busTime = busTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLatiSphere() {
		return this.latiSphere;
	}

	public void setLatiSphere(String latiSphere) {
		this.latiSphere = latiSphere;
	}

	public String getLongiSphere() {
		return this.longiSphere;
	}

	public void setLongiSphere(String longiSphere) {
		this.longiSphere = longiSphere;
	}

	public float getSpeed() {
		return this.speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getDirecton() {
		return this.directon;
	}

	public void setDirecton(float directon) {
		this.directon = directon;
	}

}