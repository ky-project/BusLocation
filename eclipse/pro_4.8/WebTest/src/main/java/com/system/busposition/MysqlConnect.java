package com.system.busposition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnect {

	private String driver = "com.mysql.jdbc.Driver";

	private String url = "jdbc:mysql://localhost:3306/";

	private String db = "gps";

	private String encoding = "?useUnicode=true&characterEncoding=UTF-8&useSSL=false";// 可加入user=root&password=~  &为连接符

	private String user = "root";

	private String password = "mysql";

	public MysqlConnect() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url + db + encoding, user, password);
	}

}
