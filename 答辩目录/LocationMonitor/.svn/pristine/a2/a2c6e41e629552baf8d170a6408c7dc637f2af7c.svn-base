package com.swust.kelab.domain;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.swust.kelab.util.JsonDateSerializer;

public class Alert {

	private Integer alertID;   		//告警ID，自增	
    private Integer alertConfigID;	//告警配置ID，告警表的外键
    private Integer locationID;		//地理位置信息ID
    private String imei;			//imei
    private String imsi;    		//imsi
    private Date posTime; 		//出现时间
    private String longitudeBaidu;	//百度经度
    private String latitudeBaidu;	//百度纬度
    private Integer areaID;			//行政区域对应的标识
    private String phoneType;		//手机型号
    private String appType;			//APP类型
    private Integer alertState;		//处理状态，0-未处理，1-已处理
    private Integer pushState;		//推送状态，-1无需推送，0未推送，1已推送
 
/*	public Alert(Location loc) {
		setLocationID(loc.getLocationID().intValue());
		setImei(loc.getImeiID());
		setImsi(loc.getImsiID());
		setPosTime(loc.getPosTime());
		setLongitudeBaidu(loc.getLongitude());
		setLatitudeBaidu(loc.getLatitudeBaidu());
		setAreaID(loc.getAreaID());
		setPhoneType(loc.getPhoneType());
		setAppType(loc.getAppType());
		setAlertState(0);
		setPushState(0);
	}*/
	
	public Integer getAlertID() {
		return alertID;
	}
	public void setAlertID(Integer alertID) {
		this.alertID = alertID;
	}
	public Integer getAlertConfigID() {
		return alertConfigID;
	}
	public void setAlertConfigID(Integer alertConfigID) {
		this.alertConfigID = alertConfigID;
	}
	public Integer getLocationID() {
		return locationID;
	}
	public void setLocationID(Integer locationID) {
		this.locationID = locationID;
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
	@JsonSerialize(using=JsonDateSerializer.class) //返回给前台的时候，将date类型格式化
	public Date getPosTime() {
		return posTime;
	}
	public void setPosTime(Date posTime) {
		this.posTime = posTime;
	}
	public String getLongitudeBaidu() {
		return longitudeBaidu;
	}
	public void setLongitudeBaidu(String longitudeBaidu) {
		this.longitudeBaidu = longitudeBaidu;
	}
	public String getLatitudeBaidu() {
		return latitudeBaidu;
	}
	public void setLatitudeBaidu(String latitudeBaidu) {
		this.latitudeBaidu = latitudeBaidu;
	}
	public Integer getAreaID() {
		return areaID;
	}
	public void setAreaID(Integer areaID) {
		this.areaID = areaID;
	}
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public Integer getAlertState() {
		return alertState;
	}
	public void setAlertState(Integer alertState) {
		this.alertState = alertState;
	}
	public Integer getPushState() {
		return pushState;
	}
	public void setPushState(Integer pushState) {
		this.pushState = pushState;
	}
}
