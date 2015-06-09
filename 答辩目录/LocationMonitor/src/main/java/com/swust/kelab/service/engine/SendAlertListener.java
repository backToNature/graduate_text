package com.swust.kelab.service.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.swust.kelab.domain.Alert;

public class SendAlertListener implements ItemWriteListener<ArrayList<Alert>> {

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Override
	public void beforeWrite(List<? extends ArrayList<Alert>> items) {

	}

	@Override
	public void afterWrite(List<? extends ArrayList<Alert>> items) {
		if (items != null)
			log.debug("Send #msg:" + items.size());
		else {
			log.debug("Send #msg:null");
		}
	}

	@Override
	public void onWriteError(Exception exception,
			List<? extends ArrayList<Alert>> items) {
		// TODO Auto-generated method stub

	}
}
