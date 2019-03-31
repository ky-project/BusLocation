package com.hibernatespring;

/**
 * Station entity. @author MyEclipse Persistence Tools
 */
public class Station extends AbstractStation implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Station() {
	}

	/** minimal constructor */
	public Station(Integer staId) {
		super(staId);
	}

	/** full constructor */
	public Station(Integer staId, String staName, float staLati, float staLongi) {
		super(staId, staName, staLati, staLongi);
	}

}
