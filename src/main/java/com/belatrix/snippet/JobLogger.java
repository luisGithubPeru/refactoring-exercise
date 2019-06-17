package com.belatrix.snippet;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belatrix.snippet.constants.MessageLevel;

/**
 * Class to save Logs on Database, Textfiles and Console with custom levels
 * MESSAGE,ERROR,WARNING
 * 
 * @author beto
 *
 */
public class JobLogger {

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logToDatabase;
	private static Logger rootLogger;
	private static Logger consoleLogger;
	private static Logger fileLogger;
	private static Logger dbLogger;
	private static String[] severities;
	private static Properties appProps;

	/**
	 * Prepare loggers
	 */
	static {
		rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		consoleLogger = LoggerFactory.getLogger("consoleLogger");
		fileLogger = LoggerFactory.getLogger("fileLogger");
		dbLogger = LoggerFactory.getLogger("dbLogger");
	}

	/**
	 * Read properties
	 */
	static {
		if (appProps == null) {
			appProps = new Properties();
			try (InputStream is = JobLogger.class.getResourceAsStream("/app.properties")) {
				appProps.load(is);
				rootLogger.debug("Loaded app.properties");
			} catch (Exception e) {
				rootLogger.warn("Could not load app.properties with error : {}", e.getMessage());
			}
		}
		
		logToFile = Boolean.parseBoolean(appProps.getProperty("logToFile"));
		logToConsole = Boolean.parseBoolean(appProps.getProperty("logToConsole"));
		logToDatabase = Boolean.parseBoolean(appProps.getProperty("logToDB"));
		String severity = appProps.getProperty("severity");
		severities = Pattern.compile(",").split(severity);
	}

	/**
	 * Logs a message using a specific level. Levels allowed are configured on
	 * app.properties Log Outputs allowed are configured on app.properties Database
	 * loads configuration from db.properties Loggers load configuration from
	 * log4j2.xml
	 * 
	 * @param messageText : String - the message to be logged
	 * @param level       : MessageLevel Enum - the level of the message
	 * @throws Exception
	 */
	public static void logMessage(String messageText, MessageLevel level) {

		// Validate input

		if (messageText == null || messageText.length() == 0) {
			return;
		}

		// Validate level

		if (level == null || Arrays.stream(severities).parallel().noneMatch(s -> s.equals(level.toString())))
			return;

		// Selectively choose destiny

		if (logToFile)
			fileLogger.info("FILE LOGGER WORKING : {} - {}", level, messageText);

		if (logToConsole)
			consoleLogger.info("CONSOLE LOGGER WORKING : {} - {}", level, messageText);

		if (logToDatabase) {
			dbLogger.info("DATABASE LOGGER WORKING : {} - {}", level, messageText);
		}
	}

	private JobLogger() {
	}
}
