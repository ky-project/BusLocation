package com.hibernatespring;

/**
 * AbstractTeacher entity provides the base persistence definition of the
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTeacher implements java.io.Serializable {

	// Fields

	private Integer teacherId;
	private String jobNumber;
	private String teaName;
	private String department;
	private Integer gender;
	private String telphone;
	private String mobile;
	private String EMail;
	private String idToken;
	private String weChatOpenId;

	// Constructors

	/** default constructor */
	public AbstractTeacher() {
	}

	/** full constructor */
	public AbstractTeacher(String jobNumber, String teaName, String department,
			Integer gender, String telphone, String mobile, String EMail,
			String idToken, String weChatOpenId) {
		this.jobNumber = jobNumber;
		this.teaName = teaName;
		this.department = department;
		this.gender = gender;
		this.telphone = telphone;
		this.mobile = mobile;
		this.EMail = EMail;
		this.idToken = idToken;
		this.weChatOpenId = weChatOpenId;
	}

	// Property accessors

	public Integer getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getJobNumber() {
		return this.jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getTeaName() {
		return this.teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}

	public String getIdToken() {
		return this.idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public String getWeChatOpenId() {
		return this.weChatOpenId;
	}

	public void setWeChatOpenId(String weChatOpenId) {
		this.weChatOpenId = weChatOpenId;
	}

}