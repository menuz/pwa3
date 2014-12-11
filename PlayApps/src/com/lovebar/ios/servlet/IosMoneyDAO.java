package com.lovebar.ios.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lovebar.dao.BaseDAO;

// 前相关的DAO
public class IosMoneyDAO extends BaseDAO {

	public void addMiDi(MiDi md) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = getConnection();
			boolean autocommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			//int inviter = 0, inviter_imei_id = 0, invited = Integer.parseInt(md
				//	.getUser_id()), invited_imei_id = 0, point = md.getCash();
			//invit(conn, pstmt, rst, inviter, inviter_imei_id, invited,
				//	invited_imei_id, point, md);

			pstmt = conn
					.prepareStatement("insert into ad_midi values(null,?,?,?,?,?,?,?,?,null)");

			pstmt.setString(1, md.getAd_id());
			pstmt.setString(2, md.getTrand_no());
			pstmt.setInt(3, md.getCash());
			pstmt.setString(4, md.getImei());
			pstmt.setString(5, md.getBundle_id());
			pstmt.setString(6, md.getUser_id());
			pstmt.setString(7, md.getApp_name());
			pstmt.setString(8, md.getSign());

			pstmt.executeUpdate();
			pstmt = conn
					.prepareStatement("update user set score=?+score where user_id=?");
			pstmt.setInt(1, md.getCash());
			pstmt.setString(2, md.getUser_id());
			pstmt.executeUpdate();

			// user_id company ad_name ad_score type
			pstmt = conn
					.prepareStatement("insert into advertise(id, user_id, company, ad_name, ad_score, type, ct) values(null, ?,?,?,?,?, null)");
			pstmt.setInt(1, Integer.parseInt(md.getUser_id()));
			pstmt.setString(2, "米迪");
			pstmt.setString(3, md.getApp_name());
			pstmt.setInt(4, md.getCash());
			pstmt.setString(5, "i");
			pstmt.executeUpdate();

			conn.commit();
			conn.setAutoCommit(autocommit);
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
	}

	
	/**
	 * 米迪验证，true表示通过
	 * 
	 * @param snuid
	 * @param time_stamp
	 * @return
	 */
	public boolean miDiCheck(String trand_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select COUNT(*) cnt from ad_midi where trand_no = ?");

			pstmt.setString(1, trand_no);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				if (rst.getInt("cnt") < 1)
					return true;
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}
		return false;
	}


}
