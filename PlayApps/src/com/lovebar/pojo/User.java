package com.lovebar.pojo;



public class User {
	int user_id;
	String webchat_name;
	String image;
	String alipy_name;
	String account;
	float total_income;
	float available_income;
	float invite_income;
	float income;
	String ct;
	int is_available;
	String ip;
	String device_id;
	
	
	// 20141105添加，因为授权获得了一些信息
	String device_os;
	String device_os_version;
	String device_model;
	int sex;
	String province;
	String openid;
	String language;
	String city;
	String country;
	int subscribe_time;
	String remark;
	
	float applyMoney;

	public User() {}

	




	



	public String getRemark() {
		return remark;
	}










	public void setRemark(String remark) {
		this.remark = remark;
	}










	public float getApplyMoney() {
		return applyMoney;
	}










	public void setApplyMoney(float applyMoney) {
		this.applyMoney = applyMoney;
	}










	public User(int user_id, String webchat_name, String image,
			String alipy_name, String account, float total_income,
			float available_income, float invite_income, float income,
			String ct, int is_available, String ip, String device_id,
			String device_os, String device_os_version, String device_model,
			int sex, String province, String openid, String language,
			String city, String country, int subscribe_time, String remark,
			float applyMoney) {
		super();
		this.user_id = user_id;
		this.webchat_name = webchat_name;
		this.image = image;
		this.alipy_name = alipy_name;
		this.account = account;
		this.total_income = total_income;
		this.available_income = available_income;
		this.invite_income = invite_income;
		this.income = income;
		this.ct = ct;
		this.is_available = is_available;
		this.ip = ip;
		this.device_id = device_id;
		this.device_os = device_os;
		this.device_os_version = device_os_version;
		this.device_model = device_model;
		this.sex = sex;
		this.province = province;
		this.openid = openid;
		this.language = language;
		this.city = city;
		this.country = country;
		this.subscribe_time = subscribe_time;
		this.remark = remark;
		this.applyMoney = applyMoney;
	}










	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", webchat_name=" + webchat_name
				+ ", image=" + image + ", alipy_name=" + alipy_name
				+ ", account=" + account + ", total_income=" + total_income
				+ ", available_income=" + available_income + ", invite_income="
				+ invite_income + ", income=" + income + ", ct=" + ct
				+ ", is_available=" + is_available + ", ip=" + ip
				+ ", device_id=" + device_id + ", device_os=" + device_os
				+ ", device_os_version=" + device_os_version
				+ ", device_model=" + device_model + ", sex=" + sex
				+ ", province=" + province + ", openid=" + openid
				+ ", language=" + language + ", city=" + city + ", country="
				+ country + ", subscribe_time=" + subscribe_time + ", remark="
				+ remark + ", applyMoney=" + applyMoney + "]";
	}



	public User(int user_id, String webchat_name, String image,
			String alipy_name, String account, float total_income,
			float available_income, float invite_income, float income,
			String ct, int is_available, String ip, String device_id) {
		super();
		this.user_id = user_id;
		this.webchat_name = webchat_name;
		this.image = image;
		this.alipy_name = alipy_name;
		this.account = account;
		this.total_income = total_income;
		this.available_income = available_income;
		this.invite_income = invite_income;
		this.income = income;
		this.ct = ct;
		this.is_available = is_available;
		this.ip = ip;
		this.device_id = device_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getWebchat_name() {
		return webchat_name;
	}

	public void setWebchat_name(String webchat_name) {
		this.webchat_name = webchat_name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAlipy_name() {
		return alipy_name;
	}

	public void setAlipy_name(String alipy_name) {
		this.alipy_name = alipy_name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public float getTotal_income() {
		return total_income;
	}

	public void setTotal_income(float total_income) {
		this.total_income = total_income;
	}

	public float getAvailable_income() {
		return available_income;
	}

	public void setAvailable_income(float available_income) {
		this.available_income = available_income;
	}

	public float getInvite_income() {
		return invite_income;
	}

	public void setInvite_income(float invite_income) {
		this.invite_income = invite_income;
	}

	public float getIncome() {
		return income;
	}

	public void setIncome(float income) {
		this.income = income;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public int getIs_available() {
		return is_available;
	}

	public void setIs_available(int is_available) {
		this.is_available = is_available;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_os() {
		return device_os;
	}

	public void setDevice_os(String device_os) {
		this.device_os = device_os;
	}

	public String getDevice_os_version() {
		return device_os_version;
	}

	public void setDevice_os_version(String device_os_version) {
		this.device_os_version = device_os_version;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(int subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	
}


