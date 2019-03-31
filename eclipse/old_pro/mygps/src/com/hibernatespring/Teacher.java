package com.hibernatespring;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */
public class Teacher extends AbstractTeacher implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** full constructor */
	public Teacher(String jobNumber, String teaName, String department,
			Integer gender, String telphone, String mobile, String EMail,
			String idToken, String weChatOpenId) {
		super(jobNumber, teaName, department, gender, telphone, mobile, EMail,
				idToken, weChatOpenId);
	}

}
