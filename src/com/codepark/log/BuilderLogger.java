package com.codepark.log;

import java.io.File;


public class BuilderLogger {

	private String Directory;

	private String fileName;
	private Level level;

	public String getDirectory() {
		return Directory;
	}

	public void setDirectory(String directory) {
		Directory = directory;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void build() {
		Strings.LOGFILE = getDirectory() + "\\" + getFileName();
		Strings.DefaultLevel = getLevel();

	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

}
