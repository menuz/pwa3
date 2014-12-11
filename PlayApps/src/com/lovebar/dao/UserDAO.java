package com.lovebar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lovebar.pojo.User;
import com.lovebar.service.CoreService;
import com.lovebar.weixin.util.WeiXinUtil;
import com.shike.util.Global;
import com.shike.util.TableLoader;
import com.shike.util.URLUtil;

public class UserDAO extends BaseDAO {

	private static InitialContext context = null;
	private DataSource dataSource = null;
	static Log log = LogFactory.getLog(UserDAO.class);
	private static final String getValidateUserQuery = "select * from user where ip = ? and is_available = 0";
	private static final String getValidateUserQuery2 = "select * from user where user_id = ? and webchat_name = ? and  ip = ? and is_available = 0";
	private static final String existValidatedUserQuery = "select * from user where user_id = ? and webchat_name = ? and  device_id = ? and is_available = 1";
	private static final String setValidateUserUpdate = "update user set device_id = ?, is_available = 1 where user_id = ? and webchat_name = ? and ip = ? and is_available = 0";
	private static final String setUserInfoQuery1 = "update user set device_id = ?, device_os = ?, device_os_version = ?, device_model = ? where user_id = ?";
	private static final String getRankingQuery = "select * from user order by total_income desc limit 10";

	private static final String getIncomeQuery = "select * from user where openid = ?";
	private static final String getUserInfoQuery = "insert into user(sex, webchat_name, remark, province, openid, language, image, city, country, subscribe_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String ifUserExistedQuery = "select * from user where openid = ?";
	private static final String ifAlipyExistedQuery = "select * from user where openid = ? and isnull(alipy_name)";
	private static final String saveAlipyInfoQuery = "update user set alipy_name = ?, account = ? where openid = ?";
	private static final String reduceMoneyQuery = "update user set available_income = 0, applyMoney=applyMoney+? where openid = ?";
	private static final String addWithdrawQuery = "insert withdraw (user_id, money, ct) values (?, ?, ?)";
	private static final String getUserQuery = "select * from user where user_id = ?";
	private static final String updateIpQuery = "update user set ip = ? where user_id = ?";
	private static final String ifExistOpenidQuery = "select * from user where openid = ?";
	private static final String updateGPSQuery = "update user set longitude = ?, latitude = ?, `precision` = ? where openid=?";
	private static final String cancelQuery = "update user set is_subscribe = 0 where openid=?";
	private static final String subscribeQuery = "update user set is_subscribe = 2 where openid=?";
	private static final String getIdByOpenidQuery = "select user_id from user where openid = ?";
	
	public UserDAO() {
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

	public ArrayList<User> getValidateUser(String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getValidateUserQuery);
			pstmt.setString(1, ip);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = TableLoader.loadUser(rs);
				validateUsers.add(user);
				System.out.println(user);
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return validateUsers;
	}

	public boolean existUnvalidatedUser(String user_id, String webchat_name,
			String ip) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getValidateUserQuery2);
			pstmt.setString(1, user_id);
			pstmt.setString(2, webchat_name);
			pstmt.setString(3, ip);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}

		return false;
	}

	public boolean existValidatedUser(String user_id, String webchat_name,
			String device_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(existValidatedUserQuery);
			pstmt.setString(1, user_id);
			pstmt.setString(2, webchat_name);
			pstmt.setString(3, device_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}

		return false;
	}

	public int setValidateUser(String user_id, String webchat_name, String ip,
			String device_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(setValidateUserUpdate);
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

	public int setUserInfo1(String device_id, String device_os,
			String device_os_version, String device_model, String user_id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(setUserInfoQuery1);
			pstmt.setString(1, device_id);
			pstmt.setString(2, device_os);
			pstmt.setString(3, device_os_version);
			pstmt.setString(4, device_model);
			pstmt.setString(5, user_id);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}

		return -1;
	}

	public int setInstalled(String user_id, String app) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = -1;
		try {
			conn = dataSource.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false); // commit trasaction manually

			String sql = "select * from installed where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();

			String _app = "";
			if (rs.next()) {
				String db_apps = rs.getString("apps");
				String[] db_apps_array = db_apps.split("[+]");
				String[] apps_array = app.split("[+]");
				Set<String> set = new HashSet<String>();
				for (String appkey : db_apps_array) {
					set.add(appkey);
				}
				for (String appkey : apps_array) {
					set.add(appkey);
				}
				StringBuffer sb = new StringBuffer(1024);
				Iterator<String> it = set.iterator();
				while (it.hasNext()) {
					String str = it.next();
					sb.append(str);
					sb.append("+");
				}
				_app = sb.toString().substring(0, sb.toString().length() - 1);

				sql = "update installed set apps = ? where user_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, _app);
				pstmt.setString(2, user_id);
				res = pstmt.executeUpdate();

			} else {
				_app = app;

				sql = "insert into installed(user_id, apps) values(?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, user_id);
				pstmt.setString(2, _app);
				res = pstmt.executeUpdate();
			}

			conn.commit();
			conn.setAutoCommit(autoCommit);

			return res;
		} catch (Exception se) {
			se.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			releaseSource(conn, pstmt, null);
		}

		return res;

	}

	public User getIncome(String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getIncomeQuery);
			pstmt.setString(1, openid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = TableLoader.loadUser(rs);
				System.out.println(user);
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return user;
	}

	public ArrayList<User> getRanking() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> ranks = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getRankingQuery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = TableLoader.loadUser(rs);
				ranks.add(user);
				System.out.println(user);
			}

		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return ranks;
	}
	
	public int getIdByOpenid(String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int user_id = 0;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getIdByOpenidQuery);
			pstmt.setString(1, openid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user_id = rs.getInt("user_id");
				return user_id;
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return user_id;
	}
	
	
	// 关注之后，获得用户的基本信息，并插入到数据库中，返回的数据目前没什么用哦
	public int getUserInfo(String OPENID) {
		// 第三方用户唯一凭证
		String appId = new Global().appId;
		// 第三方用户唯一凭证密钥
		String appSecret = new Global().appSecret;
		String ACCESS_TOKEN = WeiXinUtil.getAccessToken(appId, appSecret)
				.getToken();
		System.out.println("openid:" + OPENID);
		System.out.println("accessToken:" + ACCESS_TOKEN);

		String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="
				+ ACCESS_TOKEN + "&openid=" + OPENID;
		System.out.println(getUserInfoUrl);
		String userInfoJson = URLUtil.getJson(getUserInfoUrl);
		Map userInfoMap = URLUtil.Json2Map(userInfoJson);
		int sex = (Integer) userInfoMap.get("sex");
		String webchat_name = (String) userInfoMap.get("nickname");
		String remark = (String) userInfoMap.get("remark");
		String province = (String) userInfoMap.get("province");
		String openid = (String) userInfoMap.get("openid");
		String language = (String) userInfoMap.get("language");
		String image = (String) userInfoMap.get("headimgurl");
		String city = (String) userInfoMap.get("city");
		String country = (String) userInfoMap.get("country");
		int subscribe_time = (Integer) userInfoMap.get("subscribe_time");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int flag = 0;
		// 首先判断该openid是否已经在user表中了，即是否已关注过
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(ifUserExistedQuery);
			pstmt.setString(1, openid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = 1;
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		// 若没有关注过，就插入到user表中
		if (flag == 0) {
			try {
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(getUserInfoQuery);
				// webchat_name =
				// WeiXinUtil.isEntirelyInBasicMultilingualPlane(webchat_name);
				webchat_name = webchat_name
						.replaceAll("[^\\u0000-\\uFFFF]", "");
				pstmt.setInt(1, sex);
				pstmt.setString(2, webchat_name);
				pstmt.setString(3, remark);
				pstmt.setString(4, province);
				pstmt.setString(5, openid);
				pstmt.setString(6, language);
				pstmt.setString(7, image);
				pstmt.setString(8, city);
				pstmt.setString(9, country);
				pstmt.setInt(10, subscribe_time);
				int flag1 = pstmt.executeUpdate();
				if (flag1 == 1) {
					log.info("新用户注册，数据成功插入数据库");
					return 1; // 新用户注册，数据成功插入数据库
				} else {
					log.info("新用户注册，数据插入数据库失败。。。");
					return 0; // 新用户注册，数据插入数据库失败。。。
				}
			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				releaseSource(conn, pstmt, rs);
			}
		} else {
			log.info("该用户" + openid + "之前已经关注过");
			try {
				conn = dataSource.getConnection();
				pstmt = conn.prepareStatement(subscribeQuery);
				pstmt.setString(1, openid);
				int res = pstmt.executeUpdate();
				if (res == 1) {
					log.info("成功修改该用户" + openid + "在数据库中的字段");
				} else {
					log.info("修改该用户" + openid + "在数据库中的字段时，出现错误");
				}
			} catch (Exception se) {
				se.printStackTrace();
			} finally {
				releaseSource(conn, pstmt, rs);
			}
			return -1; // 表示用户之前已经关注过
		}
		return 2; // 这情况是说明，新用户注册，但是数据有误，
	}

	public boolean ifAlipyExisted(String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false; // 表示支付宝已经设置了
		User user = null;
		// 首先判断该openid是否已经在user表中了，即是否已关注过
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(ifAlipyExistedQuery); // 看是不是没有设置
			pstmt.setString(1, openid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true; // 表示该支付宝还没设置
				user = TableLoader.loadUser(rs);
				System.out.println(user);
			}
			System.out.println(openid);
			System.out.println(ifAlipyExistedQuery);
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return flag;
	}

	public int saveAlipyInfo(String alipy_name, String account, String open_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(saveAlipyInfoQuery);
			pstmt.setString(1, alipy_name);
			pstmt.setString(2, account);
			pstmt.setString(3, open_id);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}

	public int reduceMoney(float money, String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(reduceMoneyQuery);
			pstmt.setFloat(1, money);
			pstmt.setString(2, openid);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}

	public int addWithdraw(int user_id, float money, String ct) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(addWithdrawQuery);
			pstmt.setInt(1, user_id);
			pstmt.setFloat(2, money);
			pstmt.setString(3, ct);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}

	public int applyMoney(float money, String openid, int user_id, String ct) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(reduceMoneyQuery);
			pstmt.setFloat(1, money);
			pstmt.setString(2, openid);
			int res = pstmt.executeUpdate();
			if (res == 1) {
				System.out.println("111user表中减少" + money + "元"
						+ "，applyMoney增加了");
				pstmt = conn.prepareStatement(addWithdrawQuery);
				pstmt.setInt(1, user_id);
				pstmt.setFloat(2, money);
				pstmt.setString(3, ct);
				int res1 = pstmt.executeUpdate();
				if (res1 == 1) {
					System.out.println("111withdraw增加了" + money + "元");
				} else {
					System.out.println("111withdraw增加了失败了。。。");
				}
			} else {
				System.out
						.println("111user表available_income减少和applyMoney增加失败了。。。");
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
		return -1;
	}

	public User getUser(String user_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(getUserQuery);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = TableLoader.loadUser(rs);
				System.out.println(user);
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return user;
	}

	public int test(String name) {
		String sql = "insert into user(webchat_name) values(?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			// pstmt.setInt(1, sex);
			pstmt.setString(1, name);
			int flag1 = pstmt.executeUpdate();
			if (flag1 == 1) {
				System.out.println("新用户注册，数据成功插入数据库");
				return 1; // 新用户注册，数据成功插入数据库
			} else {
				System.out.println("新用户注册，数据插入数据库失败。。。");
				return 0; // 新用户注册，数据插入数据库失败。。。
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return 0;
	}

	public int updateIp(String user_id, String ip) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateIpQuery);
			pstmt.setString(1, ip);
			pstmt.setString(2, user_id);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}

	public int ifExistOpenid(String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(ifExistOpenidQuery);
			pstmt.setString(1, openid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, null);
		}
		return -1;
	}

	public int updateGPS(double longitude, double latitude, double precision,
			String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(updateGPSQuery);
			pstmt.setDouble(1, longitude);
			pstmt.setDouble(2, latitude);
			pstmt.setDouble(3, precision);
			pstmt.setString(4, openid);
			int res = pstmt.executeUpdate();
			return res;
		} catch (Exception se) {
			se.printStackTrace();
		} finally {
			releaseSource(conn, pstmt, rs);
		}
		return -1;
	}

	public int cancel(String openid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> validateUsers = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(cancelQuery);
			pstmt.setString(1, openid);
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