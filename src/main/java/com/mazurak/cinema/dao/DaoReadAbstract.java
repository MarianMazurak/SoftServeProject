package com.mazurak.cinema.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mazurak.cinema.db.ConnectionManager;
import com.mazurak.cinema.entity.enums.SqlQueries;

public abstract class DaoReadAbstract<TEntity> implements DaoReadInterface<TEntity> {

	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";

	protected final Map<Enum<?>, Enum<?>> sqlQueries;

	public DaoReadAbstract() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
		// TODO Call init();
	}

	// TODO Use Builder
	// TODO Use List<String>
	protected abstract TEntity createInstance(String[] args);

	// TODO Create abstract method init();
	protected abstract void init();
	// READ
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
		List<TEntity> all = new ArrayList<TEntity>();
		String[] queryResult;
//		List<String> queryResult;
		if (query == null) {
			throw new RuntimeException(QUERY_NOT_FOUND);
		}
		// change to try-with-resources DONE
		Connection connection = ConnectionManager.getInstance().getConnection();
		try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query);) {
//			queryResult = new ArrayList<>();
//			queryResult.add(resultSet.getMetaData().getColumnCount());
			queryResult = new String[resultSet.getMetaData().getColumnCount()];

			while (resultSet.next()) {
				for (int i = 0; i < queryResult.length; i++) {
//					queryResult.add(i,resultSet.getString(i + 1));
					queryResult[i] = resultSet.getString(i + 1);
				}
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		}
		if (all.isEmpty()) {
			throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
		}
		return all;
	}

	public TEntity getById(Long id) {
		return getQueryResult(String.format(sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id), SqlQueries.GET_BY_ID)
				.get(0);
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		return getQueryResult(String.format(sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), fieldName, text),
				SqlQueries.GET_BY_FIELD);
	}

	public List<TEntity> getAll() {
		return getQueryResult(
				sqlQueries.get(SqlQueries.GET_ALL).toString(), SqlQueries.GET_ALL);
	}
	
}
