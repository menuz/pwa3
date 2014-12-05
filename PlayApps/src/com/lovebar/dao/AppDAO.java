package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lovebar.pojo.App;
import com.lovebar.pojo.Try;
import com.lovebar.pojo.User;
import com.shike.util.Global;
import com.shike.util.TableLoader;

public class AppDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String showAdvsQuery = "select a.*, t.status from app a left JOIN (select * from try where user_id = ?) t on a.app_id = t.app_id where ISNULL(status) or status = 0 or status = 1";
	private static final String showDetailQuery = "select * from app where app_id = ?";
	public AppDAO() {
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
	public ArrayList<App> showAdvs(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<App> advs = new ArrayList<App>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(showAdvsQuery);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				App app = TableLoader.loadAppTry(rs);
				advs.add(app);
//				System.out.println(app);
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}	
		
		return advs;
	}
	public App showDetail(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		App app = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(showDetailQuery);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				app = TableLoader.loadApp(rs);
				System.out.println(app);
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return app;
	}
	

}