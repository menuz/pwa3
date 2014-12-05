package com.lovebar.pojo;

public class Device {
	String app;
	String deviceOS;
	String deviceOSVersion;
	String deviceId;
	String deviceModel;

	public Device(String app, String deviceOS, String deviceOSVersion,
			String deviceId, String deviceModel) {
		super();
		this.app = app;
		this.deviceOS = deviceOS;
		
		this.deviceOSVersion = deviceOSVersion;
		this.deviceId = deviceId;
		this.deviceModel = deviceModel;
	}
	
	public Device() {}

	public String getDeviceOS() {
		return deviceOS;
	}

	@Override
	public String toString() {
		return "Device [app=" + app + ", deviceOS=" + deviceOS
				+ ", deviceOSVersion=" + deviceOSVersion + ", deviceId="
				+ deviceId + ", deviceModel=" + deviceModel + "]";
	}

	public void setDeviceOS(String deviceOS) {
		this.deviceOS = deviceOS;
	}

	public String getDeviceOSVersion() {
		return deviceOSVersion;
	}

	public void setDeviceOSVersion(String deviceOSVersion) {
		this.deviceOSVersion = deviceOSVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public Device(String app) {
		super();
		this.app = app;
	}
	
}


