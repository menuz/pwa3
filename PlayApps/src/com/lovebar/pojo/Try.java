package com.lovebar.pojo;

import java.sql.Timestamp;

public class Try {
	int try_id;
	int user_id;
	int app_id;
//	String app_name;
//	String app_key;
	App app;
	int status;
	float try_time;
	String click_ct;
	String begin_ct;
	String end_ct;
	@Override
	public String toString() {
		return "Try [try_id=" + try_id + ", user_id=" + user_id + ", app_id="
				+ app_id + ", app=" + app + ", status=" + status
				+ ", try_time=" + try_time + ", click_ct=" + click_ct
				+ ", begin_ct=" + begin_ct + ", end_ct=" + end_ct + "]";
	}

	public float getTry_time() {
		return try_time;
	}

	public void setTry_time(float try_time) {
		this.try_time = try_time;
	}
	

	public Try() {}
	
	public Try(int try_id, int user_id, int app_id, int status, float try_time, String click_ct, String begin_ct, String end_ct) {
		super();
		this.try_id = try_id;
		this.user_id = user_id;
		this.app_id = app_id;
		this.status = status;
		this.try_time = try_time;
		this.click_ct = click_ct;
		this.begin_ct = begin_ct;
		this.end_ct = end_ct;
		
	}
	
	public Try(int try_id, int user_id, int app_id, App app, int status, float try_time, String click_ct, String begin_ct, String end_ct) {
		super();
		this.try_id = try_id;
		this.user_id = user_id;
		this.app_id = app_id;
//		this.app_name = app_name;
//		this.app_key = app_key;
		this.app = app;
		this.status = status;
		this.try_time = try_time;
		this.click_ct = click_ct;
		this.begin_ct = begin_ct;
		this.end_ct = end_ct;
	}
	
	
	public String getClick_ct() {
		return click_ct;
	}

	public void setClick_ct(String click_ct) {
		this.click_ct = click_ct;
	}

	public String getBegin_ct() {
		return begin_ct;
	}

	public void setBegin_ct(String begin_ct) {
		this.begin_ct = begin_ct;
	}

	public String getEnd_ct() {
		return end_ct;
	}

	public void setEnd_ct(String end_ct) {
		this.end_ct = end_ct;
	}

	
	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

	public int getTry_id() {
		return try_id;
	}
	public void setTry_id(int try_id) {
		this.try_id = try_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}


