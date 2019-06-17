package com.belatrix.snippet;

import org.junit.Test;

import com.belatrix.snippet.constants.MessageLevel;

public class JobLoggerTest {

	
	
	@Test
	public void testErrorLogMessage() {
		JobLogger.logMessage("Mensaje de ERROR", MessageLevel.ERROR);
	}

	@Test
	public void testWarningLogMessage() {
		JobLogger.logMessage("Mensaje de WARNING", MessageLevel.WARNING);
	}

	@Test
	public void testMessageLogMessage() {
		JobLogger.logMessage("Mensaje de MESSAGE", MessageLevel.MESSAGE);
	}

	@Test
	public void testNullLevel() {
		JobLogger.logMessage("Mensaje NULL", null);
	}

}
