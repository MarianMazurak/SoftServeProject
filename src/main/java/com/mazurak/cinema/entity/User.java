package com.mazurak.cinema.entity;

import com.mazurak.cinema.entity.enums.SqlQueries;

public class User extends BaseEntityCommonFieldsAbstract {
	public static enum UserEntityQueries {
		
		INSERT(SqlQueries.INSERT, "INSERT INTO users (name,login,password,roleId) values ('%s','%s','%s',%s);"),
		GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT id,name,login,password,roleId FROM users WHERE id = %s; "),
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD,"SELECT id, name,login,password,roleId from users WHERE %s = '%s'; "),
		GET_ALL(SqlQueries.GET_ALL , "SELECT id,name,login,password,roleId from users;"),
		GET_ID(SqlQueries.GET_ID, "SELECT id from users WHERE %s = '%s';"),
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE users SET name = '%s',password = '%s' WHERE id = %s;"),
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD, "UPDATE users SET %s = '%s' WHERE %s = '%s';"),
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM users where id = %s;"),
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD , "DELETE FROM users where %s = '%s';");

		private SqlQueries sqlQueries;
		private String query;

		private UserEntityQueries(SqlQueries sqlQueries, String query) {
			this.sqlQueries = sqlQueries;
			this.query = query;
		}

		public SqlQueries getSqlQueries() {
			return sqlQueries;
		}

		@Override
		public String toString() {
			return query;
		}
	}
	//id
	private String name;
	private String login;
	private String password;
	private Long roleId;
	
	public User(Long id, String name, String login, String password, Long roleId) {
		super(id);
		this.name = name;
		this.login = login;
		this.password = password;
		this.roleId = roleId;
	}
	
	
	//Getters
	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	
	public Long getRoleId() {
		return roleId;
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", login=" + login + ", password=" + password + ", roleId=" + roleId
				+ ", getId()=" + getId() + "]";
	}
	
}
