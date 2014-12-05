package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lovebar.pojo.User;
import com.shike.util.Global;
import com.shike.util.TableLoader;


public class InstalledDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String getValidateUserQuery = "select * from user where ip = ? and is_available = 0";
	private static final String setUserInfoQuery = "insert into installed(user_id, app_key, app_name)";
	public InstalledDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context
					.lookup(Global.datasource_url);
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
	public int setUserInfo(String user_id, String webchat_name, String ip,
			String device_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(setUserInfoQuery);
			pstmt.setString(1, device_id);
			pstmt.setString(2, user_id);
			pstmt.setString(3, webchat_name);
			pstmt.setString(4, ip);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}	
		
		return -1;
	}
	

}
