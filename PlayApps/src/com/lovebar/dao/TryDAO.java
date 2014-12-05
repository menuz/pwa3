package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lovebar.pojo.App;
import com.lovebar.pojo.Try;
import com.shike.util.Global;
import com.shike.util.TableLoader;

public class TryDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

//	private static final String createFaKuaidiOrder = "insert into fa_kuaidi_order(user_id, order_id, book_address) values (?, ?, ?)";
//	private static final String queryFaKuaidiOrder = "select * from fa_kuaidi_order where user_id=?";
	private static final String queryTry = "select t.id,  from try t, app a where user_id = ? ";
	private static final String getUnfinishedTask = "select * from try where user_id = ? and status != 2";
	private static final String getAppById = "select * from app where app_id = ?";
	private static final String setInstalledAppTaskQuery = "update try set status = 1, begin_ct = ? where try_id = ?";
	private static final String setFinishedTaskQuery = "update try set status = 2, end_ct = ? where try_id = ?";
	private static final String getFinishedTryQuery = "select * from try where user_id = ? and status = 2";
	private static final String insert2TryQuery = "insert try (user_id, app_id, status, try_time, click_ct) values (?, ?, 0, 0, ?)";
	private static final String ifExistQuery = "select * from try where user_id = ? and app_id = ?";
	
	private static final String updateDeletedApp = "update try set delete_ct = ? where try_id = ? and user_id = ? and app_id = ?";
	private static final String getFinishedAPP = "select * from try where user_id = ? and status = 2";
	
	public TryDAO() {
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
	
	public int test() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		int result = 1000;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(queryTry);
			rst = pstmt.executeQuery();
			if(rst.next()) {
				result = rst.getInt("try_id");
				System.out.println("result=" + result);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		return result;
	}
	
	public ArrayList<Try> getUnfinishedTask(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Try> unfinishedTasks = new ArrayList<Try>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getUnfinishedTask);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Try task = TableLoader.loadTry(rs);
				PreparedStatement pstmt1 = conn.prepareStatement(getAppById);
				pstmt1.setInt(1, task.getApp_id());
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					App app = TableLoader.loadApp(rs1);
					task.setApp(app);
				}
				unfinishedTasks.add(task);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return unfinishedTasks;
	}
	
	public int setInstalledAppTask(String try_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(setInstalledAppTaskQuery);
			pstmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setString(2, try_id);
			res = pstmt.executeUpdate();
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}
	
	public int setFinishedTask(String try_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(setFinishedTaskQuery);
			pstmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setString(2, try_id);
			res = pstmt.executeUpdate();
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}

	
	public ArrayList<Try> getFinishedTask(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Try> finishedTasks = new ArrayList<Try>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getFinishedTryQuery);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Try task = TableLoader.loadTry(rs);
				PreparedStatement pstmt1 = conn.prepareStatement(getAppById);
				pstmt1.setInt(1, task.getApp_id());
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					App app = TableLoader.loadApp(rs1);
					task.setApp(app);
				}
				finishedTasks.add(task);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return finishedTasks;
	}
	
	public int insert2Try(String user_id, String app_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(ifExistQuery);
			pstmt.setString(1, user_id);
			pstmt.setString(2, app_id);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				pstmt = conn.prepareStatement(insert2TryQuery);
				pstmt.setString(1, user_id);
				pstmt.setString(2, app_id);
				pstmt.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
				res = pstmt.executeUpdate();
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return res;
	}

	public int deleteAPP(String try_id, String user_id, String app_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int res = -1;
	
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateDeletedApp);
			pstmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
			pstmt.setInt(2, Integer.parseInt(try_id));
			pstmt.setInt(3, Integer.parseInt(user_id));
			pstmt.setInt(4, Integer.parseInt(app_id));
			
			res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		
		return -1;
	}

	public ArrayList<Try> getFinishedApp(int user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Try> unfinishedTasks = new ArrayList<Try>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getFinishedAPP);
			pstmt.setInt(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Try task = TableLoader.loadTry(rs);
				PreparedStatement pstmt1 = conn.prepareStatement(getAppById);
				pstmt1.setInt(1, task.getApp_id());
				ResultSet rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					App app = TableLoader.loadApp(rs1);
					task.setApp(app);
				}
				unfinishedTasks.add(task);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return unfinishedTasks;
	}
}
