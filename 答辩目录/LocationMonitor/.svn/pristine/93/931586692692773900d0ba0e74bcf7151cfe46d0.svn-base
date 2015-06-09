package com.swust.kelab.service.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class LocationEngineControl implements
		ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	LocationEngineThread locationEngineThread;
	
	private static Log log = LogFactory.getLog(JobLauncherDetails.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			log.debug("位置引擎启动......");
			locationEngineThread.start();
		}
	}
}
