package com.opticca.services.test.soapui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.model.testsuite.TestSuite;
import com.eviware.soapui.tools.SoapUILoadTestRunner;
import com.opticca.services.test.Scheduler;

@Component
public class TestRunner {

	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	@Value("#{'${test.suites}'.split(',')}")
	private List<String> testSuites;

	@Autowired
	private WsdlProject project;

	@Autowired
	private SoapUILoadTestRunner runner;

	public void run() {
		log.info("Run tests");
		List<TestSuite> suiteList = project.getTestSuiteList();
		for (TestSuite testSuite : suiteList) {
			if (testSuites.contains("all") || testSuites.contains(testSuite.getName())) {
				log.info("Run test suite: " + testSuite.getName());
				runner.runSuite(testSuite);
			}
		}
	}
}
