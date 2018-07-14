package com.mazurak.cinema.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager {

	private static volatile ConnectionManager instance = null;
	private DataSource dataSource;
	private Map<Long, Connection> connections;

	private ConnectionManager() {
		this.connections = new HashMap<Long, Connection>();
	}

	public static ConnectionManager getInstance() {
		return getInstance(null);
	}

	public static ConnectionManager getInstance(DataSource dataSource) {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null)
					instance = new ConnectionManager();
			}
		}
		instance.checkStatus(dataSource);
		return instance;
	}

	private void checkStatus(DataSource dataSource) {
		/*-		dataSource		this.dataSource		    Action
		 * 			null			null				create default
		 * 			null			not null			nothing
		 * 			not null		null				save dataSource
		 * 			not null		not null			if equals then nothing 
		 */
		if (dataSource == null) {
			if (getDataSource() == null) {
				setDataSource(DataSourceRepository.getDefault());
			}
		} else if ((getDataSource() == null) || (!getDataSource().equals(dataSource))) {
			setDataSource(dataSource);
		}
	}

	private DataSource getDataSource() {
		return dataSource;
	}

	// Setter новий драйвир
	private void setDataSource(DataSource dataSource) { // на випадок якщо ми захочимо змінити Driver до DB, відповідно
														// при зміні Driver потрібно закрити всі старі connections а
														// оскільки в нас можу бути зміна в багатопотоковості потрібно
														// синхронізуватися
		synchronized (ConnectionManager.class) {
			this.dataSource = dataSource;
			registerDriver();
			closeAllConnection();

		}

	}

	/*-Класс DriverManager хранит список объектов типа Driver, 
	 * которые зарегистрировались с помощью вызова DriverManager.registerDriver.
	 *  Все классы драйверов Driver должны быть написаны со статической секцией инициализации, 
	 *  в которой экземпляр данного класса создается и регистрируется в классе DriverManager при загрузке. 
	 *  Таким образом, пользователь не должен вызывать DriverManager.registerDriver непосредственно;
	 *   этот вызов автоматически делается самим драйвером при загрузке класса драйвера. 
	 *   Класс же загружается двумя способами:
	1. С помощью вызова Class.forName. Рекомендуется именно этот способ
	2. Добавлением драйвера в свойство jdbc.drivers класса java.lang.System.*/

	private void registerDriver() { // просто делегуємо метод DriverManager.registerDriver
		try {
			DriverManager.registerDriver(getDataSource().getDriver());
		} // чого не викорисатти поле dataSource
		catch (SQLException e) {
			throw new RuntimeException(ConnectionValidatorException.FAILED_REGISTRATE_DRIVER);
		}
	}

	private void addConnection(Connection connection) {
		getAllConnections().put(Thread.currentThread().getId(), connection);
	}

	private Map<Long, Connection> getAllConnections() {
		return this.connections;
	}

	public Connection getConnection() {
		Connection connection = getAllConnections().get(Thread.currentThread().getId());
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(
						getDataSource().getProtocol() + getDataSource().getProductName()
								+ getDataSource().getConnectionDetails(),
						getDataSource().getUserName(), getDataSource().getPassword());
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(ConnectionValidatorException.FAILED_CREATE_CONNECTION);
			}
			addConnection(connection);
		}

		return connection;
	}

	public void closeAllConnection() {
		if (instance != null) {
			for (Long key : instance.getAllConnections().keySet()) {
				if (instance != null) {
					try {
						instance.getAllConnections().get(key).close();
					} catch (SQLException e) {
						throw new RuntimeException(ConnectionValidatorException.FAILED_CLOSE_CONNECTION);
					}
					instance.getAllConnections().put(key, null);
				}
			}
		}
	}

	public void beginTransaction() {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionValidatorException.FAILED_CONNECTION);
		}
	}

	public void rollbackTransaction() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionValidatorException.FAILED_CONNECTION);
		}
	}

	public void commitTransaction() {
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new RuntimeException(ConnectionValidatorException.FAILED_CONNECTION);
		}
	}
}
