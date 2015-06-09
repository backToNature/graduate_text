package com.swust.kelab.service.engine;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * intern job的第一次运行需要完成的任务
 * 
 * @author libo
 *
 */
public class FirstRunTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		return null;
	}

}
