package com.swust.kelab.model;

import java.util.Date;
import java.util.List;
import com.swust.kelab.domain.LocationSimple;

public class ObjModel {
	private Integer objId;
	private String name;
	private String imei;
	private String imsi;
	private String phone;
	private String time;
	private String longitudeA;
	private String latitudeA;
	private String longitudeB;
	private String latitudeB;
	private Date dateStart;
	private Date dateEnd;
	private List<LocationSimple> items;
	private QueryData locData;
	public Integer getObjId() {
		return objId;
	}
	public void setObjId(Integer objId) {
		this.objId = objId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public List<LocationSimple> getItems() {
		return items;
	}
	public void setItems(List<LocationSimple> items) {
		this.items = items;
	}
	public String getLongitudeA() {
		return longitudeA;
	}
	public void setLongitudeA(String longitudeA) {
		this.longitudeA = longitudeA;
	}
	public String getLatitudeA() {
		return latitudeA;
	}
	public void setLatitudeA(String latitudeA) {
		this.latitudeA = latitudeA;
	}
	public String getLongitudeB() {
		return longitudeB;
	}
	public void setLongitudeB(String longitudeB) {
		this.longitudeB = longitudeB;
	}
	public String getLatitudeB() {
		return latitudeB;
	}
	public void setLatitudeB(String latitudeB) {
		this.latitudeB = latitudeB;
	}
	public QueryData getLocData() {
		return locData;
	}
	public void setLocData(QueryData locData) {
		this.locData = locData;
	}
	
}
