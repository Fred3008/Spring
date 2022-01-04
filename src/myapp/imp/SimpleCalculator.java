package myapp.imp;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import myapp.services.ICalculator;
import myapp.services.iLogger;

@Service("SimpleCalculator")
@Qualifier("test")
public class SimpleCalculator implements ICalculator {

	private iLogger logger;

	@PostConstruct
	public void start() {
		if (logger == null) {
			throw new IllegalStateException("null logger");
		}
		System.err.println("start " + this);
	}

	@PreDestroy
	public void stop() {
		System.err.println("stop " + this);
	}

	public int add(int a, int b) {
		logger.log(String.format("add(%d,%d)", a, b));
		return (a + b);
	}

	public iLogger getLogger() {
		return logger;
	}

	@Autowired
	@Qualifier("test")
	public void setLogger(iLogger logger) {
		this.logger = logger;
	}

}
