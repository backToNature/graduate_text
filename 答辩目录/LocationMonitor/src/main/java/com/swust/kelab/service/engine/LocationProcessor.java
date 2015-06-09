package com.swust.kelab.service.engine;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.swust.kelab.domain.Alert;
import com.swust.kelab.domain.Location;
import com.swust.kelab.domain.LocationRaw;
import com.swust.kelab.service.AlertService;
import com.swust.kelab.service.LocationService;

public class LocationProcessor implements
		ItemProcessor<LocationRaw, ArrayList<Alert>> {

	@Autowired
	AlertService alertService;
	@Autowired
	LocationService locationService;

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	public ArrayList<Alert> process(LocationRaw item) {
		ArrayList<Alert> alert = null;

		log.info("processing item" + item.getLocationID());

		try {
			alert = alertService.createAlert(item);
		} catch (Exception e) {
			alert = null;
			log.error(e.getMessage());
			// e.printStackTrace();
		}

		log.debug(item.getImei());
		log.debug(item.getLocationID());

		return alert;
	}
}
