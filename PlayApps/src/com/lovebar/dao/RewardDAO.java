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
import com.lovebar.pojo.Reward;
import com.lovebar.pojo.Try;
import com.shike.util.Global;
import com.shike.util.TableLoader;

public class RewardDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;

	private static final String getAppIncom = "select outcome from app where app_id = ?";
	private static final String finishAppInsert = "insert into reward (user_id, try_id, app_id, reward, type) values(?,?,?,?,?)";
	private static final String finishAppUpdateUser = "update user set total_income = total_income + ?, income = income + ?, available_income = available_income + ? where user_id = ?";
	private static final String finishAppUpdateApp = "update app set installed_count = installed_count + 1 where app_id = ?";
	private static final String getInvitorIdQuery = "select invitor_id from invite where invitee_id = ? and status = 1";
	private static final String getInvitorIdRewardCountQuery = "select count(*) cnt from reward where invitor_id = ? and user_id = ?";
	private static final String tuiguangAppInsert = "insert into reward (user_id, invitor_id, try_id, app_id, reward, type) values(?,?,?,?,?,?)";
	private static final String tuiguangAppUpdateUser = "update user set invite_income = invite_income + ?, total_income = total_income + ?, available_income = available_income + ? where user_id = ?";
	
	public RewardDAO() {
		try {
			if (context == null) {
				context = new InitialContext();
			}
			dataSource = (DataSource) context.lookup(Global.datasource_url);
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

	public void finishApp(String user_id, String try_id, String app_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(getAppIncom);
			pstmt.setString(1, app_id);
			rs = pstmt.executeQuery(); // get app income

			if (rs.next()) {
				double outcome = rs.getDouble("outcome");

				pstmt = conn.prepareStatement(finishAppInsert);
				pstmt.setString(1, user_id);
				pstmt.setString(2, try_id);
				pstmt.setString(3, app_id);
				pstmt.setDouble(4, outcome);
				pstmt.setInt(5, Reward.REWARD_TYPE_FINISH_APP);
				int res = pstmt.executeUpdate();  // insert into table

				pstmt = conn.prepareStatement(finishAppUpdateUser);
				pstmt.setDouble(1, outcome);
				pstmt.setDouble(2, outcome);
				pstmt.setDouble(3, outcome);
				pstmt.setString(4, user_id);
				res = pstmt.executeUpdate();  // finishAppUpdateUser
				
				pstmt = conn.prepareStatement(finishAppUpdateApp);
				pstmt.setString(1, app_id);
				res = pstmt.executeUpdate();  // updateAppTable install_count++
				
				pstmt = conn.prepareStatement(getInvitorIdQuery);  
				pstmt.setString(1, user_id);
				rs = pstmt.executeQuery();  // exist shangxian

				if (rs.next()) {
					int invitor_id = rs.getInt("invitor_id");
					String invitee_id = user_id;

					pstmt = conn.prepareStatement(getInvitorIdRewardCountQuery);
					pstmt.setInt(1, invitor_id);
					pstmt.setString(2, invitee_id);
					rs = pstmt.executeQuery();     // shangxian tuiguang 的 个数 < 10
					
					if (rs.next()) {
						int cnt = rs.getInt("cnt");
						if (cnt < Reward.REWARD_TUIGUANG_MAX_APP_COUNT) {   			

							pstmt = conn.prepareStatement(tuiguangAppInsert);
							pstmt.setString(1, user_id);
							pstmt.setInt(2, invitor_id);
							pstmt.setString(3, try_id);
							pstmt.setString(4, app_id);
							pstmt.setDouble(5, Reward.REWARD_TUIGUANG_MONEY);
							pstmt.setInt(6, Reward.REWARD_TYPE_TUIGUANG_APP);
							pstmt.executeUpdate();   // insert tuiguang app in reward
							
							pstmt = conn.prepareStatement(tuiguangAppUpdateUser);
							pstmt.setDouble(1, Reward.REWARD_TUIGUANG_MONEY);
							pstmt.setDouble(2, Reward.REWARD_TUIGUANG_MONEY);
							pstmt.setDouble(3, Reward.REWARD_TUIGUANG_MONEY);
							pstmt.setInt(4, invitor_id);
							res = pstmt.executeUpdate();  // update invitor's money
						}
					}
				}
			}

			conn.commit();
			conn.setAutoCommit(autoCommit);
		} catch (Exception se) {
			se.printStackTrace();
			System.err.println("fatal error");
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			releaseSource(conn, pstmt, rs);
		}
	}

}
