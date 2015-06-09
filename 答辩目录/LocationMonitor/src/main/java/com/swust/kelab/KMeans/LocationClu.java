package com.swust.kelab.KMeans;

public class LocationClu {

	private int id; 
	
	  
	/* 经度 */  
	@KmeanField
	private double longitude;  
	  
	/* 纬度*/  
@KmeanField
	private double latitude;  
	  
	public double getLongitude() {  
	    return longitude;  
	}  
	  
	public void setLongitude(double longitude) {  
	    this.longitude = longitude;  
	}  
	  
	public double getLatitude() {  
	    return latitude;  
	}  
	  
	public void setLatitude(double latitude) {  
	    this.latitude = latitude;  
	}  
	  
	public int getId() {  
	    return id;  
	}  
	  
public void setId(int id){
	this.id = id;
}
	  
}
