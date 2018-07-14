package com.mazurak.cinema.db;

import java.sql.Driver;
import java.sql.SQLException;

public final class DataSourceRepository {
	
	private DataSourceRepository() {
	}

	public static DataSource getDefault() {
		return getMySqlLocalHost();
	}

	public static DataSource getMySqlLocalHost() {
		Driver driver;
		try {
			driver = new com.mysql.jdbc.Driver();
		} catch (SQLException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(ConnectionValidatorException.FAILED_JDBC_DRIVER);
		}
		return new DataSource(driver, "jdbc:", "mysql:", "//localhost:3306/softservecinema?useSSL=false", "root",
				"shadow12345");
	}
}
