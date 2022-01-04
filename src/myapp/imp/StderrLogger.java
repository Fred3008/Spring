package myapp.imp;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import myapp.services.iLogger;

@Service("StderrLogger")
@Qualifier("test")
public class StderrLogger  implements iLogger {

	@PostConstruct
	public void start() {
		System.err.println("Strat " + this);
	}
	@PreDestroy
	public void stop() {
		System.err.println("Stop " + this);
	}
	
	@Override
	public void log(String message) {
		System.err.printf("%tF %1$tR | %s\n" , new Date(), message);
	}
}
