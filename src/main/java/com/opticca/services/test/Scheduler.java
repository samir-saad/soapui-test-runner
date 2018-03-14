package com.opticca.services.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.opticca.services.test.soapui.TestRunner;

@Component
public class Scheduler {

	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	@Autowired
	private TestRunner runner;

	@Scheduled(cron = "${test.schedule}")
	public void schedule() {
		log.info("Scheduled test started.");
		runner.run();
	}
}
