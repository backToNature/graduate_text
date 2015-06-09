package com.swust.kelab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swust.kelab.domain.Location;
import com.swust.kelab.domain.LocationRaw;
import com.swust.kelab.domain.User;
import com.swust.kelab.repos.LocationDAO;

@Service("LocationService")
public class LocationService {
	@Autowired
	private LocationDAO locationDAO;

	public int add(Location location) throws Exception {
		return locationDAO.add(location);

	}

	public int delete(Location location) throws Exception {
		return locationDAO.delete(location);
	}

	public int updateOne(Location location) {
		return locationDAO.update(location);

	}

	public Location selectIMEI(String imei) {
		return locationDAO.selectIMEI(imei);

	}

	public Location selectIMSI(String imsi) {
		return locationDAO.selectIMSI(imsi);

	}
}
