package com.swust.kelab.model;

import java.util.Date;
import java.util.List;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.swust.kelab.domain.Alert;
import com.swust.kelab.util.JsonDateSerializer;

public class ConfigAlertModel {
	private int ObjId;
	private int alertConfigID;		//自增主键
	private Date configTime;		//配置时间，格式 yyyy-mm-dd hh:mm:ss；
	private int monitorType;		//对象类型，0-IMEI，1-IMSI
	private String imei;
	private String imsi;
	private String longitudeA;  	//构成矩形区域的经纬度
	private String longitudeB;
	private String latitudeA;
	private String latitudeB;
	private int pushFlag;			//是否推送，0-否，1-是
	private List<Alert> alertList;
	
	public int getObjId() {
		return ObjId;
	}
	public void setObjId(int objId) {
		ObjId = objId;
	}
	public int getAlertConfigID() {
		return alertConfigID;
	}
	public void setAlertConfigID(int alertConfigID) {
		this.alertConfigID = alertConfigID;
	}
	
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getConfigTime() {
		return configTime;
	}
	public void setConfigTime(Date configTime) {
		this.configTime = configTime;
	}
	public int getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(int monitorType) {
		this.monitorType = monitorType;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getLongitudeA() {
		return longitudeA;
	}
	public void setLongitudeA(String longitudeA) {
		this.longitudeA = longitudeA;
	}
	public String getLongitudeB() {
		return longitudeB;
	}
	public void setLongitudeB(String longitudeB) {
		this.longitudeB = longitudeB;
	}
	public String getLatitudeA() {
		return latitudeA;
	}
	public void setLatitudeA(String latitudeA) {
		this.latitudeA = latitudeA;
	}
	public String getLatitudeB() {
		return latitudeB;
	}
	public void setLatitudeB(String latitudeB) {
		this.latitudeB = latitudeB;
	}
	public int getPushFlag() {
		return pushFlag;
	}
	public void setPushFlag(int pushFlag) {
		this.pushFlag = pushFlag;
	}
	public List<Alert> getAlertList() {
		return alertList;
	}
	public void setAlertList(List<Alert> alertList) {
		this.alertList = alertList;
	}
}
