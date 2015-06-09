package com.swust.kelab.serviceTest;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.SyncTaskExecutor;

import com.swust.kelab.service.AccountService;
import com.swust.kelab.service.engine.LocationEngineService;

public class LocationEnginTest {

	SimpleJobLauncher launcher;
	JobExecution je;
	Map<String, JobParameter> parameters;
	Job jb;
	LocationEngineService locationService;

	@Before
	public void init() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "classpath:spring/applicationContext.xml",
						"classpath:spring/dao.xml",
						"classpath:spring/service.xml",
						"classpath:spring/locationEngine.xml" });
		launcher = new SimpleJobLauncher();
		launcher.setJobRepository((JobRepository) ctx.getBean("jobRepository"));
		launcher.setTaskExecutor(new SyncTaskExecutor());
		// jb = (Job) ctx.getBean("locationEngineJob");
		jb = (Job) ctx.getBean("locationEngineJobWrapper");
		locationService = ctx.getBean(LocationEngineService.class);
	}

	//@Test
	public void startLocationEngineJob() {
		JobParametersBuilder jpBuilder = new JobParametersBuilder();

		try {
			// jpBuilder.addLong("pageSize",new Long(Long.MAX_VALUE));
			// jpBuilder.addLong("startID",new Long(10));
			// jpBuilder.addLong("endID",new Long(20));
			// jpBuilder.addString("pageSize","10");
			jpBuilder.addString("cursor", "1");
			// jpBuilder.addString("endID","20");
			jpBuilder.addString("Date", (new Date()).toString());
			je = launcher.run(jb, jpBuilder.toJobParameters());
			System.out.println(je);
			System.out.println(je.getJobInstance());
			System.out.println(je.getStepExecutions());
			// Thread.sleep(100000);
		} catch (JobInstanceAlreadyCompleteException e) {

		} catch (JobExecutionAlreadyRunningException e) {
			// TODO Auto-generated catch block
		} catch (JobRestartException e) {
			// TODO Auto-generated catch block
		} catch (JobParametersInvalidException e) {
			// TODO Auto-generated catch block
		}
	}

	// @Test
	public void startQuartz() throws InterruptedException {
		Thread.sleep(100000);
	}

	@Test
	public void startLocationEngine() throws InterruptedException {
		Thread.sleep(100000);
	}
}
