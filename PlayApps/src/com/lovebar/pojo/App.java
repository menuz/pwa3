package com.lovebar.pojo;

import java.sql.Timestamp;

public class App {
	int app_id;
	String app_name;
	String type;
	String image;
	String comment;
	float income;
	float outcome;
	int count;
	int installed_count;
	String ct;
	String keyword;
	int rank;
	String app_key;
	int status; //增加一个字段，这个字段为-1表示没有在玩，0表示正在玩
	String require;
	String search_image;
	String os_type;
	String url;
	
	
	
	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key, int status, String require, String search_image,
			String os_type, String url) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
		this.status = status;
		this.require = require;
		this.search_image = search_image;
		this.os_type = os_type;
		this.url = url;
	}


	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key, int status, String require, String search_image,
			String os_type) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
		this.status = status;
		this.require = require;
		this.search_image = search_image;
		this.os_type = os_type;
	}


	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key, int status, String require, String search_image) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
		this.status = status;
		this.require = require;
		this.search_image = search_image;
	}


	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key, String require, String search_image) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
		this.require = require;
		this.search_image = search_image;
	}


	


	


	@Override
	public String toString() {
		return "App [app_id=" + app_id + ", app_name=" + app_name + ", type="
				+ type + ", image=" + image + ", comment=" + comment
				+ ", income=" + income + ", outcome=" + outcome + ", count="
				+ count + ", installed_count=" + installed_count + ", ct=" + ct
				+ ", keyword=" + keyword + ", rank=" + rank + ", app_key="
				+ app_key + ", status=" + status + ", require=" + require
				+ ", search_image=" + search_image + ", os_type=" + os_type
				+ ", url=" + url + "]";
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key, int status) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
		this.status = status;
	}




	public App() {
		super();
	}
	
	
	public App(int app_id, String app_name, String type, String image,
			String comment, float income, float outcome, int count,
			int installed_count, String ct, String keyword, int rank,
			String app_key) {
		super();
		this.app_id = app_id;
		this.app_name = app_name;
		this.type = type;
		this.image = image;
		this.comment = comment;
		this.income = income;
		this.outcome = outcome;
		this.count = count;
		this.installed_count = installed_count;
		this.ct = ct;
		this.keyword = keyword;
		this.rank = rank;
		this.app_key = app_key;
	}
	public int getApp_id() {
		return app_id;
	}
	public void setApp_id(int app_id) {
		this.app_id = app_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public float getIncome() {
		return income;
	}
	public void setIncome(float income) {
		this.income = income;
	}
	public float getOutcome() {
		return outcome;
	}
	public void setOutcome(float outcome) {
		this.outcome = outcome;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getInstalled_count() {
		return installed_count;
	}
	public void setInstalled_count(int installed_count) {
		this.installed_count = installed_count;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getApp_key() {
		return app_key;
	}
	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}


	public String getRequire() {
		return require;
	}


	public void setRequire(String require) {
		this.require = require;
	}


	public String getSearch_image() {
		return search_image;
	}


	public void setSearch_image(String search_image) {
		this.search_image = search_image;
	}


	public String getOs_type() {
		return os_type;
	}


	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
}
