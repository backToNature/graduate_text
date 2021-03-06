package com.swust.kelab.domain;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.swust.kelab.util.JsonDateSerializer;

public class LocationSimple {
	private Integer dataId;
	private String longitude;
	private String latitude;
	private Date time;
	private String phoneType; //手机型号
	private String appType; //APP应用类型
	private String srcIP;
	private String dstIP;
	private String timeStr;

	private Integer area;
	
	public Integer getDataId() {
		return dataId;
	}
	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	@JsonSerialize(using=JsonDateSerializer.class) //返回给前台的时候，将date类型格式化
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	public String getSrcIP() {
		return srcIP;
	}
	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}
	public String getDstIP() {
		return dstIP;
	}
	public void setDstIP(String dstIP) {
		this.dstIP = dstIP;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	
}
