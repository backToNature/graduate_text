package com.swust.kelab.service.engine;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * wrapper job的两个step, recover.batchJob 和current.batchJob, 执行前获取参数
 * 
 * @author libo
 *
 */
public class JobExecutionWindowParametersExtractor implements
		JobParametersExtractor {

	private JobRepository jobRepository;

	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public JobParameters getJobParameters(Job job, StepExecution stepExecution) {

		/**
		 * 从wrapper job的上下文中获取last execution
		 */
		// Read the last execution from the wrapping job
		// in order to build Next Execution Window
		JobExecution lastExecution = (JobExecution) stepExecution
				.getJobExecution().getExecutionContext()
				.get(JobLauncherDetails.ENGINEJOB_NAME);

		/**
		 * 确定参数
		 */
		if (null != lastExecution) {
			// 如果last execution退出状态是FAILED，参数就是last execution的参数
			if (ExitStatus.FAILED.equals(lastExecution.getExitStatus())) {
				// JobInstance instance = lastExecution.getJobInstance();
				JobParameters parameters = lastExecution.getJobParameters();
				log.debug("JobExecutionWindowParametersExtractor.jobParameters="
						+ parameters);
				return parameters;
			}
		}

		// 第一次执行或者last execution退出状态不是FAILED，则重新生成参数
		// We do not have failed execution or have no execution at all we need
		// to create a new execution window
		return buildJobParamaters(lastExecution, stepExecution);
	}

	/**
	 * 
	 * @description 根据execution和stepExecution生成job执行参数
	 * @author libo
	 * @date 2015年4月29日 下午2:52:46
	 *
	 * @param jobExecution
	 * @param stepExecution
	 * @return
	 */
	private JobParameters buildJobParamaters(JobExecution jobExecution,
			StepExecution stepExecution) {
		JobParameters jobParameters = null;

		if (jobExecution != null) {
			// UNKNOWN and COMPLETED:
			// 从jobRepository中取得jobExecution中的cursor和readCount
			int readCount = 0;
			int cursor = 0;
			JobParametersBuilder builder = new JobParametersBuilder();
			StepExecution execution = jobRepository.getLastStepExecution(
					jobExecution.getJobInstance(),
					JobLauncherDetails.ENGINESTEP_NAME);
			readCount = execution.getReadCount();
			cursor = Integer.parseInt(jobExecution.getJobParameters()
					.getString(JobLauncherDetails.CURSOR_NAME));
			cursor += readCount;

			builder.addString(JobLauncherDetails.CURSOR_NAME,
					String.valueOf(cursor));

			builder.addString("Date", new Date().toString());

			jobParameters = builder.toJobParameters();
		} else {
			// FIRST_RUN: 从step的execution中获取参数
			jobParameters = stepExecution.getJobExecution().getJobParameters();
		}

		log.debug("JobExecutionWindowParametersExtractor.jobParameters="
				+ jobParameters);

		return jobParameters;
	}
}