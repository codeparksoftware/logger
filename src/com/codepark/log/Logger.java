package com.codepark.log;

public class Logger {

	public void log(Level level, String msg) {

		logToFile(level, msg);
	}

	public void log(String msg) {
		logToFile(Strings.DefaultLevel, msg);
	}

	public void debug(String msg) {
		logToFile(Level.Debug, msg);
	}

	public void info(String msg) {
		logToFile(Level.Info, msg);

	}

	public void error(String msg) {
		logToFile(Level.Error, msg);
	}

	private void logToFile(Level level, String msg) {
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		// Here will be task
	

		LogWriter.getLogWriter().setClassName(stackTraceElements[stackTraceElements.length - 2].getClassName() + " in "
				+ stackTraceElements[stackTraceElements.length - 2].getFileName());
		LogWriter.getLogWriter()
				.setMethodName("Line :" + stackTraceElements[stackTraceElements.length - 2].getLineNumber()
						+ " Method :" + stackTraceElements[stackTraceElements.length - 2].getMethodName());
		LogWriter.getLogWriter().setPath(Strings.LOGFILE);

		LogWriter.getLogWriter().log(level, msg);

	}
}
