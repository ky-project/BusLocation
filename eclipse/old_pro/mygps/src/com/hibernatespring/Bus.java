package com.hibernatespring;

/**
 * Bus entity. @author MyEclipse Persistence Tools
 */
public class Bus extends AbstractBus implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Bus() {
	}

	/** minimal constructor */
	public Bus(Integer busId) {
		super(busId);
	}

	/** full constructor */
	public Bus(Integer busId, String type, Integer seatSum, String license,
			String driverName, String driverTel) {
		super(busId, type, seatSum, license, driverName, driverTel);
	}

}
