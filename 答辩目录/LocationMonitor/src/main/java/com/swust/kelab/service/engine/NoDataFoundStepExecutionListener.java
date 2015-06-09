package com.swust.kelab.service.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class NoDataFoundStepExecutionListener implements StepExecutionListener {

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		int countRead = stepExecution.getReadCount();
		log.info("afterStep, countRead = " + countRead);
		if (countRead == 0) {
			return ExitStatus.NOOP;
		}
		return null;
	}

}
