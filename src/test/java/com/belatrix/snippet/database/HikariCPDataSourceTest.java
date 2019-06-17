package com.belatrix.snippet.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class HikariCPDataSourceTest {

	
	@Test
	public void testGetConnection() {
		try {
			Connection con = HikariCPDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
