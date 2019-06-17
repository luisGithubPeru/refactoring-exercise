package com.belatrix.snippet;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belatrix.snippet.constants.MessageLevel;

/**
 * Class to save Logs on Database, Textfiles and Console
 * 
 * @author beto
 *
 */
public class JobLogger {

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	private static boolean logToDatabase;
	private static Map dbParams;
	private static Logger rootLogger;
	private static Logger consoleLogger;
	private static Logger fileLogger;
	private static Logger dbLogger;
	private static String severity;
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
				rootLogger.warn("Could not load app.properties with error : " + e.getMessage());
			}
		}
		logToFile = Boolean.parseBoolean(getProperty("logToFile"));
		logToConsole = Boolean.parseBoolean(getProperty("logToConsole"));
		logToDatabase = Boolean.parseBoolean(getProperty("logToDB"));
		severity = getProperty("severity");
	}

	public static String getProperty(String name) {

		rootLogger.debug("returning property: " + name + " - " + appProps.getProperty(name));
		return appProps.getProperty(name);
	}

	/**
	 * @param messageText : the message to be logged
	 * @throws Exception
	 */
	public static void LogMessage(String messageText, MessageLevel level) {

		if (messageText == null || messageText.length() == 0 || level == null) {
			return;
		}

		// Selectively choose destiny

		if (logToFile)
			fileLogger.info("FILE LOGGER WORKING : " + level + " - " + messageText);

		if (logToConsole)
			consoleLogger.info("CONSOLE LOGGER WORKING : " + level + " - " + messageText);

		if (logToDatabase) {
			dbLogger.info("DATABASE LOGGER WORKING  : " + level + " - " + messageText);
		}
	}

}
