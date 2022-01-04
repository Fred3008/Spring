package myapp.imp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import myapp.services.iLogger;

public class BeanFileLogger implements iLogger {

	private String fileName;
	private PrintWriter writer;

	@PostConstruct
	public void start() {
		if (fileName == null) {
			throw new IllegalStateException("no fileName");
		}
		try {
			OutputStream os = new FileOutputStream(fileName, true);
			this.writer = new PrintWriter(os);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("bad filname");
		}
		System.err.println("start " + this);
	}

	// stop services
	@PreDestroy
	public void stop() {
		writer.close();
		System.err.println("stop " + this);
	}

	@Override
	public void log(String message) {
		writer.printf("%tF %1$tR | %s\n", new Date(), message);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
