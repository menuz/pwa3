package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lovebar.pojo.Invite;
import com.lovebar.pojo.User;
import com.shike.util.Global;
import com.shike.util.TableLoader;

public class InviteDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String getInviteRecordsQuery = "select * from invite where invitor_id = ?";
	private static final String addIpQuery = "insert into invite(invitor_id, ip) values(?, ?)";
	private static final String isIpExistQuery = "select * from invite where invitor_id = ? and ip = ?";
	private static final String isInvitedQuery = "select * from invite where ip = ?";
	private static final String addInviteeQuery = "update invite set invitee_id = ? where ip = ?";
	
	public InviteDAO() {
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
	public ArrayList<Invite> getInviteRecords(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Invite> inviteRecords = new ArrayList<Invite>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getInviteRecordsQuery);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Invite invite = TableLoader.loadInvite(rs);
				inviteRecords.add(invite);
				System.out.println(invite);
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return inviteRecords;
	}
	
	
	
	public int addIp(int userid, String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(addIpQuery);
			pstmt.setInt(1, userid);
			pstmt.setString(2, ip);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}
	
	public int isIpExist(int user_id, String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(isIpExistQuery);
			pstmt.setInt(1, user_id);
			pstmt.setString(2, ip);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return 0;
	}
	
	public int isInvited(String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(isInvitedQuery);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return 0;
	}

	public int addInvitee(int invitee_id, String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(addInviteeQuery);
			pstmt.setInt(1, invitee_id);
			pstmt.setString(2, ip);
			return pstmt.executeUpdate();
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return 0;
	}
}