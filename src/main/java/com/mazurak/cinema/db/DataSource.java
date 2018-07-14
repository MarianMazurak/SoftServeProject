package com.mazurak.cinema.db;

import java.sql.Driver;

public final class DataSource {

		private Driver driver;
		private String protocol; // => jdbc:
		private String productName; // => mysql:
		private String connectionDetails; // => //localhost:8080/softservecinema
		private String userName;
		private String password;

		// TODO Create Factory, Builder
		public DataSource(Driver driver, String protocol, String productName, String connectionDetails, String userName,
				String password) {
			this.driver = driver;
			this.protocol = protocol;
			this.productName = productName;
			this.connectionDetails = connectionDetails;
			this.userName = userName;
			this.password = password;
		}

		//Getter
		public Driver getDriver() {
			return driver;
		}

		public String getProtocol() {
			return protocol;
		}

		public String getProductName() {
			return productName;
		}

		public String getConnectionDetails() {
			return connectionDetails;
		}

		public String getUserName() {
			return userName;
		}

		public String getPassword() {
			return password;
		}
		
		//Setter
		public void setDriver(Driver driver) {
			this.driver = driver;
		}

		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public void setConnectionDetails(String connectionDetails) {
			this.connectionDetails = connectionDetails;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
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
