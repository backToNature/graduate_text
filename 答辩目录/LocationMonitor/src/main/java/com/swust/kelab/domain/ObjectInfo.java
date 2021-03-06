package com.swust.kelab.domain;

public class ObjectInfo {
	private int objectID;		//自增主键
	private int IMEI_IMEIID;
	private int IMSI_IMSIID;
	private String objectName;	
	private String objectPhone;	
	private String objectIMSI;	
	private String objectIMEI;	
	private String displayName;	//可用于百度地图气泡显示
	private String description;	
	private String Identify;	
	public int getObjectID() {
		return objectID;
	}
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	public int getIMEI_IMEIID() {
		return IMEI_IMEIID;
	}
	public void setIMEI_IMEIID(int iMEI_IMEIID) {
		IMEI_IMEIID = iMEI_IMEIID;
	}
	public int getIMSI_IMSIID() {
		return IMSI_IMSIID;
	}
	public void setIMSI_IMSIID(int iMSI_IMSIID) {
		IMSI_IMSIID = iMSI_IMSIID;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectPhone() {
		return objectPhone;
	}
	public void setObjectPhone(String objectPhone) {
		this.objectPhone = objectPhone;
	}
	public String getObjectIMSI() {
		return objectIMSI;
	}
	public void setObjectIMSI(String objectIMSI) {
		this.objectIMSI = objectIMSI;
	}
	public String getObjectIMEI() {
		return objectIMEI;
	}
	public void setObjectIMEI(String objectIMEI) {
		this.objectIMEI = objectIMEI;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIdentify() {
		return Identify;
	}
	public void setIdentify(String identify) {
		Identify = identify;
	}


}
