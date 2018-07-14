package com.mazurak.cinema.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.mazurak.cinema.db.ConnectionManager;
import com.mazurak.cinema.entity.BaseEntity;
import com.mazurak.cinema.entity.enums.SqlQueries;

public abstract class  DaoCRUDAbstract<TEntity extends BaseEntity> extends DaoReadAbstract<TEntity> implements DaoCRUDInterface<TEntity> {

protected final static String DATABASE_INPUT_ERROR = "Database Input Error";
	
	protected DaoCRUDAbstract() {
	}
	
	// TODO Use Builder
	// TODO Use List<String>
		protected abstract String[] getFields(TEntity entity);

		// TODO Use List<String>
		protected abstract String[] getUpdateFields(TEntity entity);
		private boolean executeQuery(String query, SqlQueries sqlQueries) {
			boolean result = false;
			if(query == null) {
				throw new RuntimeException(QUERY_NOT_FOUND);
			} // change to try-with-resources DONE
			Connection connection = ConnectionManager.getInstance().getConnection();
			try(Statement statement = connection.createStatement();) {
				System.out.println(connection.isClosed());
				result = statement.execute(query);
			}
			 catch (SQLException e) {
				throw new RuntimeException(DATABASE_INPUT_ERROR);
			}
			return result;
		}
		
		//Create
		public boolean insert(TEntity entity) {
			String query = String.format(sqlQueries.get(
					SqlQueries.INSERT).toString(), 
					(Object[]) Arrays.copyOfRange(getFields(entity), 1, getFields(entity).length));
			return executeQuery(query, SqlQueries.INSERT);
		}
		
		//Update

		public boolean updateByEntity(TEntity entity) {
			String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
						(Object[]) getUpdateFields(entity));
			return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
		}
		
		public boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition) {
			String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_FIELD).toString(),
						fieldName, text, fieldCondition, textCondition);
			return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
		}

		// Delete
		public boolean deleteById(Long id) {
			String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_ID).toString(),
						id);
			System.out.println("query=" + query);
			return executeQuery(query, SqlQueries.DELETE_BY_ID);
		}

		public boolean deleteByFieldName(String fieldCondition, String textCondition) {
			String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_FIELD).toString(),
						fieldCondition, textCondition);
			System.out.println(query);
			return executeQuery(query, SqlQueries.DELETE_BY_FIELD);
		}

		public boolean delete(TEntity entity) {
			return deleteById(entity.getId());
		}
}