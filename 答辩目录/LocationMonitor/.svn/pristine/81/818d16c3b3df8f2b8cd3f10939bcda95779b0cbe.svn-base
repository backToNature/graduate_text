package com.swust.kelab.service.engine;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * 等待数据的重试时间
 * 
 * @author libo
 *
 */
public class WaitingTasklet implements Tasklet {

	long sleepTime;
	
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		Thread.sleep(sleepTime);
		return null;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

}
