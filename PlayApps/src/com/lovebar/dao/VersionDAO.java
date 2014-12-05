package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lovebar.weixin.pojo.Version;
import com.shike.util.Global;
import com.shike.util.TableLoader;

public class VersionDAO extends BaseDAO {
	
	private static InitialContext context = null;
	private DataSource dataSource = null;

	
	public VersionDAO() {
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

	/**
	 * i表示ios，a表示android,获得最新版本号
	 * 
	 * @param type
	 * @return
	 */
	public Version getVersion(String type) { //

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		Version version = null;
		try {
			conn = getConnection();
			pstmt = conn
					.prepareStatement("select type,version,path,info,size from version  where id=(select  MAX(id) from version where type=?)");

			pstmt.setString(1, type);
			rst = pstmt.executeQuery();

			if (rst.next()) {
				version = new Version();
				version.setType(rst.getString("type"));
				version.setVersion(rst.getString("version"));
				version.setPath(rst.getString("path"));
				version.setSize(rst.getDouble("size"));
				version.setInfo(rst.getString("info"));
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rst);
		}

		return version;
	}

}
