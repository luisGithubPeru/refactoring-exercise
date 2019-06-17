package com.belatrix.snippet;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;

public class JobLoggerTest {

	JobLogger jobLogger;
	boolean logToFile;
	boolean logToConsole;
	boolean logMessage;
	boolean logWarning;
	boolean logError;
	boolean logToDatabase;
	Map<String, String> dbParams;

	@Before
	public void setUp() throws Exception {
		dbParams = new HashMap<String, String>();
		dbParams.put("userName", "postgres");
		dbParams.put("password", "root");
		dbParams.put("dbms", "postgresql");
		dbParams.put("serverName", "localhost");
		dbParams.put("portNumber", "5432");
		dbParams.put("logFileFolder", "C:/Users/beto");
		logToConsole = logToFile = logToDatabase = true;
		logMessage = logWarning = logError = true;
		jobLogger = new JobLogger(logToFile, logToConsole, logToDatabase, logMessage, logWarning, logError, dbParams);
	}

	@Test
	public void testLogMessage() {
		try {
			JobLogger.LogMessage("messageText", true, true, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
