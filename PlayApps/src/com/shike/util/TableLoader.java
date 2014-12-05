package com.shike.util;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.lovebar.pojo.App;
import com.lovebar.pojo.Installed;
import com.lovebar.pojo.Invite;
import com.lovebar.pojo.Try;
import com.lovebar.pojo.User;

public class TableLoader {
	public static App loadApp(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int app_id = rs.getInt("app_id");
		String app_name = rs.getString("app_name");
		String type = rs.getString("type");
		String image = rs.getString("image");
		String comment = rs.getString("comment");
		float income = rs.getFloat("income");
		float outcome = rs.getFloat("outcome");
		int count = rs.getInt("count");
		int installed_count = rs.getInt("installed_count");
		String ct = rs.getTimestamp("ct") == null ? "" : rs.getTimestamp("ct")
				.toString();
		String keyword = rs.getString("keyword");
		int rank = rs.getInt("rank");
		String app_key = rs.getString("app_key");
		String require = rs.getString("require");
		String search_image = rs.getString("search_image");
		

		App app = new App(app_id, app_name, type, image, comment, income,
				outcome, count, installed_count, ct, keyword, rank, app_key, require, search_image);

		return app;
	}

	public static App loadAppTry(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int app_id = rs.getInt("app_id");
		String app_name = rs.getString("app_name");
		String type = rs.getString("type");
		String image = rs.getString("image");
		String comment = rs.getString("comment");
		float income = rs.getFloat("income");
		float outcome = rs.getFloat("outcome");
		int count = rs.getInt("count");
		int installed_count = rs.getInt("installed_count");
		String ct = rs.getTimestamp("ct") == null ? "" : rs.getTimestamp("ct")
				.toString();
		String keyword = rs.getString("keyword");
		int rank = rs.getInt("rank");
		String app_key = rs.getString("app_key");
		String require = rs.getString("require");
		String search_image = rs.getString("search_image");
		int status = rs.getInt("status");
		String os_type = rs.getString("os_type");
		String url = rs.getString("url");

		App app = new App(app_id, app_name, type, image, comment, income,
				outcome, count, installed_count, ct, keyword, rank, app_key, status, require, search_image, os_type, url
				);

		return app;
	}

	public static Try loadTry(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int try_id = rs.getInt("try_id");
		int user_id = rs.getInt("user_id");
		int app_id = rs.getInt("app_id");
		int status = rs.getInt("status");
		float try_time = rs.getFloat("try_time");
		String click_ct = rs.getTimestamp("click_ct") == null ? "" : rs
				.getTimestamp("click_ct").toString();
		String begin_ct = rs.getTimestamp("begin_ct") == null ? "" : rs
				.getTimestamp("begin_ct").toString();
		String end_ct = rs.getTimestamp("end_ct") == null ? "" : rs
				.getTimestamp("end_ct").toString();
		Try t = new Try(try_id, user_id, app_id, status, try_time, click_ct,
				begin_ct, end_ct);

		return t;
	}

	public static User loadUser(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int user_id = rs.getInt("user_id");
		String webchat_name = rs.getString("webchat_name");
		String image = rs.getString("image");
		String alipy_name = rs.getString("alipy_name");
		String account = rs.getString("account");
		float total_income = rs.getFloat("total_income");
		float available_income = rs.getFloat("available_income");
		float invite_income = rs.getFloat("invite_income");
		float income = rs.getFloat("income");
		String ct = rs.getTimestamp("ct") == null ? "" : rs.getTimestamp("ct")
				.toString();
		int is_available = rs.getInt("is_available");
		String ip = rs.getString("ip");
		String device_id = rs.getString("device_id");
		
		String device_os = rs.getString("device_os");
		String device_os_version = rs.getString("device_os_version");
		String device_model = rs.getString("device_model");
		int sex = rs.getInt("sex");
		String province = rs.getString("province");
		String openid = rs.getString("openid");
		String language = rs.getString("language");
		String city = rs.getString("city");
		String country = rs.getString("country");
		int subscribe_time = rs.getInt("subscribe_time");
		String remark = rs.getString("remark");
		float applyMoney = rs.getFloat("applyMoney");
		

		User user = new User(user_id, webchat_name, image, alipy_name, account,
				total_income, available_income, invite_income, income, ct,
				is_available, ip, device_id, device_os, device_os_version, device_model,
				sex, province, openid, language,
				city, country, subscribe_time, remark, applyMoney);

		return user;
	}

	public static Installed loadInstalled(java.sql.ResultSet rs)
			throws SQLException, UnsupportedEncodingException {
		int installed_id = rs.getInt("installed_id");
		int user_id = rs.getInt("user_id");
		String app_key = rs.getString("app_key");
		String app_name = rs.getString("app_name");
		Installed installed = new Installed(installed_id, user_id, app_key,
				app_name);
		return installed;
	}

	public static Invite loadInvite(java.sql.ResultSet rs) throws SQLException,
			UnsupportedEncodingException {
		int invite_id = rs.getInt("invite_id");
		int invitor_id = rs.getInt("invitor_id");
		int invitee_id = rs.getInt("invitee_id");
		String ip = rs.getString("ip");
		int status = rs.getInt("status");
		String ct = rs.getTimestamp("ct") == null ? "" : rs.getTimestamp("ct")
				.toString();

		Invite invite = new Invite(invite_id, invitor_id, invitee_id, ip,
				status, ct);

		return invite;
	}
}
