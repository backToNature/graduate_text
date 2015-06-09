package com.swust.kelab.service.engine;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.core.repository.JobRepository;

public class RecoveryFlowDecider implements JobExecutionDecider {
	private static final String FIRST_RUN = "FIRST_RUN";
	private static final String CURRENT = "CURRENT";
	private static final String RECOVER = "RECOVER";
	private static final String RUNNING = "RUNNING";

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	private JobExplorer jobExplorer;

	private JobRepository jobRepository;

	public void setJobExplorer(JobExplorer jobExplorer) {
		this.jobExplorer = jobExplorer;
	}

	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {
		// the wrapper is named as the wrapped job + WRAPPER
		String wrapperJobName = jobExecution.getJobInstance().getJobName();
		String jobName;
		jobName = wrapperJobName.substring(0,
				wrapperJobName.indexOf(JobLauncherDetails.WRAPPER_NAME));

		/**
		 * decide FIRST_RUN
		 */
		// test if internal job instance existed
		List<JobInstance> instances = jobExplorer
				.getJobInstances(jobName, 0, 1);
		JobInstance internalJobInstance = instances.size() > 0 ? instances
				.get(0) : null;
		if (null == internalJobInstance) {
			log.debug("Flow status is: " + FIRST_RUN);
			return new FlowExecutionStatus(FIRST_RUN);
		}
		// test if internal job execution existed
		List<JobExecution> jobExecutions = jobExplorer
				.getJobExecutions(internalJobInstance);
		JobExecution internJobExecution = jobExecutions.size() > 0 ? jobExecutions
				.get(0) : null;
		if (null == internJobExecution) {
			log.debug("Flow status is: " + FIRST_RUN);
			return new FlowExecutionStatus(FIRST_RUN);
		}

		/**
		 * get last execution of intern job
		 */
		JobExecution lastExecution = jobRepository.getLastJobExecution(
				internalJobInstance.getJobName(),
				internJobExecution.getJobParameters());
		log.debug("lastExecution: " + lastExecution);

		/**
		 * decide RUNNING
		 */
		if (lastExecution.isRunning()) {
			log.debug("Flow status is: " + RUNNING);
			// return new FlowExecutionStatus(RUNNING);
		}

		/**
		 * save last execution of intern job to wrapper context
		 */
		// place the last execution on the context (wrapper context to use
		// later)
		jobExecution.getExecutionContext().put(
				JobLauncherDetails.ENGINEJOB_NAME, lastExecution);
		ExitStatus exitStatus = lastExecution.getExitStatus();
		log.debug("exitStatus: " + exitStatus);

		/**
		 * decide RECOVER and CURRENT
		 */
		if (ExitStatus.FAILED.equals(exitStatus)
				|| ExitStatus.UNKNOWN.equals(exitStatus)) {
			log.debug("Flow status is: " + RECOVER);
			return new FlowExecutionStatus(RECOVER);
		} else if (ExitStatus.COMPLETED.equals(exitStatus)) {
			log.debug("Flow status is: " + CURRENT);
			return new FlowExecutionStatus(CURRENT);
		}
		// We should never get here unless we have a defect
		throw new RuntimeException("Unexpecded batch status: " + exitStatus
				+ " in decider!");

	}
}