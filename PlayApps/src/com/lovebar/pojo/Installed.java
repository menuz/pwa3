package com.lovebar.pojo;

public class Installed {
	int installed_id;
	int user_id;
	String app_key;
	String app_name;
	
	
	public Installed() {
		super();
	}
	public Installed(int installed_id, int user_id, String app_key,
			String app_name) {
		super();
		this.installed_id = installed_id;
		this.user_id = user_id;
		this.app_key = app_key;
		this.app_name = app_name;
	}
	public int getInstalled_id() {
		return installed_id;
	}
	public void setInstalled_id(int installed_id) {
		this.installed_id = installed_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	
}
