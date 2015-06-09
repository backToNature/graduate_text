package com.swust.kelab.service.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class LocationEngineThread extends Thread {
	@Autowired
	LocationEngineService locationEngineService;

	boolean run;

	public void run() {
		if (run)
			locationEngineService.startEngine();
	}

	public void setRun(boolean run) {
		this.run = run;
	}
}
