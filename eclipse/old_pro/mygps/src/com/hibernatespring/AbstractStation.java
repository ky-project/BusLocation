package com.hibernatespring;

/**
 * AbstractStation entity provides the base persistence definition of the
 * Station entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractStation implements java.io.Serializable {

	// Fields

	private Integer staId;
	private String staName;
	private float staLati;
	private float staLongi;

	// Constructors

	/** default constructor */
	public AbstractStation() {
	}

	/** minimal constructor */
	public AbstractStation(Integer staId) {
		this.staId = staId;
	}

	/** full constructor */
	public AbstractStation(Integer staId, String staName, float staLati,
			float staLongi) {
		this.staId = staId;
		this.staName = staName;
		this.staLati = staLati;
		this.staLongi = staLongi;
	}

	// Property accessors

	public Integer getStaId() {
		return this.staId;
	}

	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public String getStaName() {
		return this.staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

	public float getStaLati() {
		return this.staLati;
	}

	public void setStaLati(float staLati) {
		this.staLati = staLati;
	}

	public float getStaLongi() {
		return this.staLongi;
	}

	public void setStaLongi(float staLongi) {
		this.staLongi = staLongi;
	}

}