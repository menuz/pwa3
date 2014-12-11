/**
 * 文件名：BaseDAO.java
 *
 * 版本信息： version 1.0
 * 日期：2013-9-13
 * Copyright by menuz
 */
package com.lovebar.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDAO {
//	private static String driver = "com.mysql.jdbc.Driver";
	// private static String url =
	// "jdbc:mysql://localhost:3306/sms?useUnicode=true&amp;characterEncoding=GBK";
	// private static String url =
	// "jdbc:mysql://192.168.1.67:3306/sms?useUnicode=true&amp;characterEncoding=utf8";
	// private static String url =
	// "jdbc:mysql://192.168.1.67:3306/sms?useUnicode=true&characterEncoding=utf8";
	
//	private static String url = "jdbc:mysql://zhijiantaojin:3306/makemoney?useUnicode=true&characterEncoding=utf8";
//	private static String user = "root";
//	private static String password = "gpsserver!@#$%";

	private static InitialContext context = null;
	private DataSource dataSource = null;

	public BaseDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context
					.lookup("java:comp/env/jdbc/shike");

		} catch (NamingException e2) {
			e2.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
		}
		return conn;
	}

	// /**
	// *
	// */
	// public Connection getConn() {
	// Connection conn = null;
	// try {
	// conn = DriverManager.getConnection(url, user, password);
	//
	// if (conn!= null && !conn.isClosed())
	// System.out.println("Succeeded connecting to the Database!");
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// return conn;
	// }

	/**
	 * 
	 * 此方法描述的是：
	 * 
	 * @author: dmnrei@gmail.com
	 * @version: 2013-3-31 下午4:48:47
	 */
	public void releaseSource(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 此方法描述的是：
	 * 
	 * @author: dmnrei@gmail.com
	 * @version: 2013-3-31 下午4:48:47
	 */
	public void releaseSource(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
