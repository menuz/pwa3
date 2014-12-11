package com.lovebar.ios.servlet;

public class MiDi {
	private int id;
	private String ad_id;
	private String trand_no;
	private int cash;
	private String imei;
	private String bundle_id;
	private String user_id;
	private String app_name;
	private String sign;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd_id() {
		return ad_id;
	}
	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}
	public String getTrand_no() {
		return trand_no;
	}
	public void setTrand_no(String trand_no) {
		this.trand_no = trand_no;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getBundle_id() {
		return bundle_id;
	}
	public void setBundle_id(String bundle_id) {
		this.bundle_id = bundle_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public MiDi(String ad_id, String trand_no, int cash,
			String imei, String bundle_id, String user_id, String app_name,
			String sign) {
		super();
		this.ad_id = ad_id;
		this.trand_no = trand_no;
		this.cash = cash;
		this.imei = imei;
		this.bundle_id = bundle_id;
		this.user_id = user_id;
		this.app_name = app_name;
		this.sign = sign;
	}
	
	public MiDi() {}
	@Override
	public String toString() {
		return "MiDi [id=" + id + ", ad_id=" + ad_id + ", trand_no=" + trand_no
				+ ", cash=" + cash + ", imei=" + imei + ", bundle_id="
				+ bundle_id + ", user_id=" + user_id + ", app_name=" + app_name
				+ ", sign=" + sign + "]";
	}
	
	
	
}


