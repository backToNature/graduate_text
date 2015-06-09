package com.swust.kelab.service.engine;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.stereotype.Service;

@Service(value = "locationEngineService")
/**
 * 管理位置引擎的启动，停止，查询是否运行
 * @author libo
 *
 */
public class LocationEngineService {

	JobExecution je = null;
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	String jobName;
	String cursor;
	JobLocator jobLocator;
	JobLauncher jobLauncher;
	JobOperator jobOperator;

	/**
	 * 
	 * @description 启动位置信息处理引擎
	 * @author libo
	 * @date 2015年5月5日 下午6:47:08
	 *
	 * @return 成功与否
	 */
	public boolean startEngine() {
		JobParametersBuilder jpBuilder = new JobParametersBuilder();

		try {
			jpBuilder.addString("cursor", cursor);
			jpBuilder.addString("Date", (new Date()).toString());
			je = jobLauncher.run(jobLocator.getJob(jobName),
					jpBuilder.toJobParameters());

			log.debug("JobExecution is " + je);
			log.debug("JobInstance is: " + je.getJobInstance());
			log.debug("StepExecutions are: " + je.getStepExecutions());
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobExecutionAlreadyRunningException e) {
			e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) {
			e.printStackTrace();
		} catch (NoSuchJobException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * public boolean stopEngine() throws NoSuchJobException,
	 * NoSuchJobExecutionException, JobExecutionNotRunningException { Set<Long>
	 * executions = jobOperator.getRunningExecutions(jobName); for (Long
	 * execution : executions) { jobOperator.stop(execution); } return true; }
	 * 
	 * public boolean isRunning() throws NoSuchJobException { Set<Long>
	 * executions = jobOperator.getRunningExecutions(jobName); if (executions !=
	 * null && executions.size() > 0) { for (Long execution : executions) {
	 * log.debug("execution: " + execution); } return true; } else return false;
	 * }
	 */
	public String getSummary() throws NoSuchJobExecutionException,
			NoSuchJobException {
		Set<Long> executions = jobOperator.getRunningExecutions(jobName);
		String strSummary = "";
		Long id = 0L;
		if (executions != null && executions.size() > 0) {
			for (Long execution : executions) {
				if (id < execution)
					id = execution;
			}
			strSummary += jobOperator.getSummary(id);
			strSummary += "<br/>";
		}
		return strSummary;
	}

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public void setJobOperator(JobOperator jobOperator) {
		this.jobOperator = jobOperator;
	}
}
