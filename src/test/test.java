package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import myapp.imp.BeanFileLogger;
import myapp.imp.FileLogger;
import myapp.imp.SimpleCalculator;
import myapp.imp.StderrLogger;
import myapp.services.ICalculator;
import myapp.services.iLogger;

public class test {

	@Test
	public void testBeanFileLogger() {
		// create the service
		BeanFileLogger logger = new BeanFileLogger();
		logger.setFileName("tmp/myapp.log");
		// set parameter
		logger.start();
		// use
		use(logger);
		// stop
		logger.stop();
	}

	@Test
	public void testFileLogger() {
		// create the service
		FileLogger logger = new FileLogger("tmp/myapp.log");

		// start the service
		logger.start();
		// use the service
		use(logger);
		// stop the service
		logger.stop();
	}

	@Before
	public void beforeEachTest() {
		System.err.println("==========");
	}

	@After
	public void afterEachTest() {

	}

	// use a logger
	void use(iLogger logger) {
		logger.log("Voila le résultat");
	}
	// test logger

	@Test
	public void testStderrLogger() {
		// create the service
		StderrLogger logger = new StderrLogger();

		// start the service
		logger.start();
		// use the service
		use(logger);
		// stop the service
		logger.stop();
	}

	void use(ICalculator calc) {
		calc.add(100, 200);
	}

	@Test
	public void testCalculatorAndStderrLogger() {

		StderrLogger logger = new StderrLogger();
		logger.start();

		SimpleCalculator calculator = new SimpleCalculator();
		calculator.setLogger(logger);
		calculator.start();
		use(calculator);
		calculator.stop();
		logger.stop();

	}

	@Test
	public void testCalculatorWithLogger() {
		System.err.println("+++ CalculatorWithLogger");
		String conf = "config.xml";
		try (

				AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(conf);) {
			ICalculator calc = ctx.getBean("calculator", ICalculator.class);
			use(calc);
		}
	}
}
