package com.belatrix.snippet.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	private static Properties dbProps;
	private static Logger logger = LoggerFactory.getLogger(HikariCPDataSource.class);

	static {
		dbProps = new Properties();
		try (InputStream is = HikariCPDataSource.class.getResourceAsStream("/db.properties")) {
			dbProps.load(is);
			logger.debug("Loaded db.properties");
		} catch (Exception e) {
			logger.debug("Could not load app.properties with error : " + e.getMessage());
		}
	}

	public static Connection getConnection() throws SQLException {
		if (ds == null) {
			config.setJdbcUrl(dbProps.getProperty("url"));
			config.setUsername(dbProps.getProperty("user"));
			config.setPassword(dbProps.getProperty("password"));
			ds = new HikariDataSource(config);
		}
		return ds.getConnection();
	}

	private HikariCPDataSource() {
	}
}
