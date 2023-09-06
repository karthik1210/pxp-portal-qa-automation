//Copyright 2023 NXGN Management, LLC. All Rights Reserved.

package com.pxp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.pxp.base.TestBase;
import com.pxp.queries.DataNotPopulatedException;

public class DBUtils {

	public static Connection ngConnection = null;


	public Connection getConnection(String url, String user, String paswd) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ngConnection = DriverManager.getConnection(url, user, paswd);

			TestBase.log.info(url);
			TestBase.log.info("====================Database connected successfully======================");
		} catch (Exception e) {
			TestBase.log.info("==========Error Occurred while connecting to sql==========");
		}
		return ngConnection;
	}

	public static void closeConnection() throws Exception {
		if (ngConnection != null) {
			ngConnection.close();
		}
		TestBase.log.info("Database disconnected successfully");
	}

	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet rs = null;
		try {
			if (ngConnection == null) {
				TestBase.log.info("Connection is null");
			}
			Statement stmt = ngConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(query);
		} catch (final SQLException e) {
			TestBase.log.info("==========Something went wrong while executing query==========");
		}
		return rs;
	}

	public int executeUpdateQuery(String query) throws SQLException {
		int rs = 0;
		try {
			if (ngConnection == null) {
				TestBase.log.info("Connection is null");
			}
			Statement stmt = ngConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeUpdate(query);
		} catch (SQLException e) {
			TestBase.log.info("==========Something went wrong while executing query==========");
		}
		return rs;
	}

	public boolean isDataPopulatedForTable(String query) throws DataNotPopulatedException {
		try {
			ResultSet rs = null;
			int interval = 30000; // Set the initial interval time to 30 seconds
			boolean isLogPrinted = false; // Set the flag to false initially
				for (int i = 0; i <= 6; i++) {
				rs = executeQuery(query);
				if (!isLogPrinted) { // Check if the log has been printed or not
					TestBase.log.info("Trying Query : " + query);
					isLogPrinted = true; // Set the flag to true after printing the log
				}
				if (rs.next()) { // Check if the query returned any rows
					return true; // Return true if data is found
				}
				Thread.sleep(interval); // Wait for the specified interval time before trying again
				TestBase.log.info((interval / 1000) + " seconds passed...");
				interval += 30000; // Increment the interval time by 30 seconds
			}
			// Throw a custom exception if no data is found after 3 minutes
			throw new DataNotPopulatedException("Failed to find data in table after trying for 3 minutes.");
		} catch (Exception e) {
			throw new DataNotPopulatedException("Failed to query table due to Some exception: " + e.getMessage());
		}
	}

	public String newId() throws SQLException {
		String query = "select newid()";
		ResultSet rs = executeQuery(query);
		String value = "";
		while (rs.next()) {
			value = rs.getString(1);
		}
		return value;
	}

	public String getDate() throws SQLException {
		String query = "select getdate()";
		ResultSet rs = executeQuery(query);
		String value = "";
		while (rs.next()) {
			value = rs.getString(1);
		}
		return value;
	}
	

}
