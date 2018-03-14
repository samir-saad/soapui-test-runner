package com.opticca.services.test.soapui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.xmlbeans.XmlException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eviware.soapui.impl.WorkspaceImpl;
import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.support.types.StringToStringMap;
import com.eviware.soapui.tools.SoapUILoadTestRunner;

@Configuration
public class SoapUIBeans {

	@Value("${test.project}")
	private String soapuiProject;

	@Value("${test.endpoint}")
	private String endpoint;
	
	@Value("${test.threads-count}")
	private int threadsCount;

	@Value("${test.duration}")
	private int duration;

	@Bean
	public WsdlProject getProject() throws XmlException, IOException {
		InputStream is = new FileInputStream(soapuiProject);

		WorkspaceImpl workspace = new WorkspaceImpl("", new StringToStringMap());

		return new WsdlProject(is, workspace);
	}

	@Bean
	public SoapUILoadTestRunner getLoadTestRunner() {
		SoapUILoadTestRunner runner = new SoapUILoadTestRunner();
		runner.setPrintReport(true);
		runner.setEndpoint(endpoint);
		runner.setThreadCount(threadsCount);
		runner.setLimit(duration);

		return runner;
	}
}
