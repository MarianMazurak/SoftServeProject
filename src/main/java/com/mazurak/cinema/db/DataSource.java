package com.mazurak.cinema.db;

import java.sql.Driver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
@AllArgsConstructor
public final class DataSource {

		private Driver driver;
		private String protocol; 
		private String productName; 
		private String connectionDetails;
		private String userName;
		private String password;

		@Override
		public boolean equals(Object dataSource) {
			boolean result = false;
			if (dataSource instanceof DataSource) {
				result = getDriver().getClass().getName().equals(((DataSource) dataSource).getDriver().getClass().getName())
						&& getProtocol().equals(((DataSource) dataSource).getProtocol())
						&& getProductName().equals(((DataSource) dataSource).getProductName())
						&& getConnectionDetails().equals(((DataSource) dataSource).getConnectionDetails())
						&& getUserName().equals(((DataSource) dataSource).getUserName())
						&& getPassword().equals(((DataSource) dataSource).getPassword());
			}
			return result;
		}
}
