package com.belatrix.snippet;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.belatrix.snippet.constants.MessageLevel;

public class JobLoggerTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testErrorLogMessage() {
		JobLogger.LogMessage("Mensaje de ERROR", MessageLevel.ERROR);
	}

	@Test
	public void testWarningLogMessage() {
		JobLogger.LogMessage("Mensaje de WARNING", MessageLevel.WARNING);
	}

	@Test
	public void testMessageLogMessage() {
		JobLogger.LogMessage("Mensaje de MESSAGE", MessageLevel.MESSAGE);
	}

	@Test
	public void testNullLevel() {
		JobLogger.LogMessage("Mensaje NULL", null);
	}

}
