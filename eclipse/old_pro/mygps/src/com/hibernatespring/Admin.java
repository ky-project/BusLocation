package com.hibernatespring;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
public class Admin extends AbstractAdmin implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String adminId) {
		super(adminId);
	}

	/** full constructor */
	public Admin(String adminId, String password, String adminName,
			String telphone, String mobile, String root) {
		super(adminId, password, adminName, telphone, mobile, root);
	}

}
