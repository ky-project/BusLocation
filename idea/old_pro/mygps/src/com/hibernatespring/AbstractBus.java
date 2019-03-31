package com.hibernatespring;

/**
 * AbstractBus entity provides the base persistence definition of the Bus
 * entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBus implements java.io.Serializable {

	// Fields

	private Integer busId;
	private String type;
	private Integer seatSum;
	private String license;
	private String driverName;
	private String driverTel;

	// Constructors

	/** default constructor */
	public AbstractBus() {
	}

	/** minimal constructor */
	public AbstractBus(Integer busId) {
		this.busId = busId;
	}

	/** full constructor */
	public AbstractBus(Integer busId, String type, Integer seatSum,
			String license, String driverName, String driverTel) {
		this.busId = busId;
		this.type = type;
		this.seatSum = seatSum;
		this.license = license;
		this.driverName = driverName;
		this.driverTel = driverTel;
	}

	// Property accessors

	public Integer getBusId() {
		return this.busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSeatSum() {
		return this.seatSum;
	}

	public void setSeatSum(Integer seatSum) {
		this.seatSum = seatSum;
	}

	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverTel() {
		return this.driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

}