package com.opticca.services.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opticca.services.test.soapui.TestRunner;

@RestController
public class RestAPI {

	@Autowired
	private TestRunner runner;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@RequestMapping("/run")
	public void run() {
		runner.run();
	}
}
