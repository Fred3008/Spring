package myapp.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import myapp.imp.StderrLogger;
import myapp.services.iLogger;

public class TestLoggerServices {

	@Before
	public void beforeEachTest() {
		System.err.println("==========");
	}
	
	@After
	public void afterEachTest() {
		
	}
	
	//use a logger
	void use(iLogger logger) {
		logger.log("Voila le résultat");
	}
	//test logger
	
	@Test
	public void testStderrLogger() {
		//create the service
		StderrLogger logger = new StderrLogger();
		
		//start the service 
		logger.start();
		//use the service
		use(logger);
		//stop the service
		logger.stop();
	}
}
