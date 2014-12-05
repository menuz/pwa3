package com.lovebar.pojo;

public class Reward {
	public static int REWARD_TYPE_INVITE_FRIEND = 0;
	public static int REWARD_TYPE_TUIGUANG_APP = 1;
	public static int REWARD_TYPE_FINISH_APP = 2;
	
	public static int REWARD_TUIGUANG_MAX_APP_COUNT = 10;
	
	public static double REWARD_TUIGUANG_MONEY = 1.0;
	
	int reward_id;
	int user_id;
	int invisted_id;
	float reward;
	int type;
	String ct;
	public int getReward_id() {
		return reward_id;
	}
	public void setReward_id(int reward_id) {
		this.reward_id = reward_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getInvisted_id() {
		return invisted_id;
	}
	public void setInvisted_id(int invisted_id) {
		this.invisted_id = invisted_id;
	}
	public float getReward() {
		return reward;
	}
	public void setReward(float reward) {
		this.reward = reward;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	
}
