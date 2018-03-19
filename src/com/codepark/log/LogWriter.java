package com.codepark.log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class LogWriter {

	private static Object MUTEX = new Object();

	private String className;
	private String methodName;
	private String path;

	private LogWriter() {

	}

	protected static LogWriter logWriter;

	public static LogWriter getLogWriter() {
		if (logWriter == null) {
			synchronized (MUTEX) {
				if (logWriter == null) {

					logWriter = new LogWriter();
					return logWriter;
				}
			}
		} else
			return logWriter;
		return logWriter;

	}

	@SuppressWarnings("deprecation")
	public void log(Level level, String msg) {

		if (!Files.exists(Paths.get(getPath()))) {
			try {
				Files.createFile(Paths.get(getPath()));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		try {
			Files.write(Paths.get(getPath()), System.getProperty("line.separator").getBytes(),
					StandardOpenOption.APPEND);
			// each log entry has own line

			Files.write(Paths.get(getPath()), (new Date().toLocaleString() + "\t").getBytes(),
					StandardOpenOption.APPEND);
			System.out.println(new Date().toString());

			Files.write(Paths.get(getPath()), (level.toString() + ":  " + getClassName() + "\t").getBytes(),
					StandardOpenOption.APPEND);
			System.out.println((level.toString() + ":  " + getClassName()));
			Files.write(Paths.get(getPath()), ("\t  " + getMethodName() + "\t").getBytes(), StandardOpenOption.APPEND);
			System.out.println(getMethodName());

			Files.write(Paths.get(getPath()), System.getProperty("line.separator").getBytes(),
					StandardOpenOption.APPEND);
			Files.write(Paths.get(getPath()), (msg + "\t").getBytes(), StandardOpenOption.APPEND);
			System.out.println(msg);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
