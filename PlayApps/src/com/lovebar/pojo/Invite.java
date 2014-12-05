package com.lovebar.pojo;

public class Invite {

	int invite_id;
	int invitor_id;
	int invitee_id;
	String ip;
	int status;
	String ct;
	
	
	
	@Override
	public String toString() {
		return "Invite [invite_id=" + invite_id + ", invitor_id=" + invitor_id
				+ ", invitee_id=" + invitee_id + ", ip=" + ip + ", status="
				+ status + ", ct=" + ct + "]";
	}
	public Invite() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Invite(int invite_id, int invitor_id, int invitee_id, String ip,
			int status, String ct) {
		super();
		this.invite_id = invite_id;
		this.invitor_id = invitor_id;
		this.invitee_id = invitee_id;
		this.ip = ip;
		this.status = status;
		this.ct = ct;
	}
	public int getInvite_id() {
		return invite_id;
	}
	public void setInvite_id(int invite_id) {
		this.invite_id = invite_id;
	}
	public int getInvitor_id() {
		return invitor_id;
	}
	public void setInvitor_id(int invitor_id) {
		this.invitor_id = invitor_id;
	}
	public int getInvitee_id() {
		return invitee_id;
	}
	public void setInvitee_id(int invitee_id) {
		this.invitee_id = invitee_id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	
}
