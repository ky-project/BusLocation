package com.system.busposition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WriteToMysql {
	
	private String sign;
	
	private Connection connection = null;

	public WriteToMysql(String string) {
		this.sign = string;
	}
	
	public void connect() {
		
		try {
			connection = new MysqlConnect().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public synchronized void write() {
		
		String sql_insert = "insert into testtable(test) values (?)";
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(sql_insert);
			ps.setString(1, sign);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
