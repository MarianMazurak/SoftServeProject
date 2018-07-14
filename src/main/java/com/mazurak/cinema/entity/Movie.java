package com.mazurak.cinema.entity;

import com.mazurak.cinema.entity.enums.SqlQueries;

public class Movie extends BaseEntityCommonFieldsAbstract {
	
	public static enum MovieEntityQueries {

		INSERT(SqlQueries.INSERT,"INSERT INTO movies (filmName,description,ageLimit,year,idUser) values ('%s' ,'%s' , '%s', %s, %s);"), 
		GET_BY_ID(SqlQueries.GET_BY_ID,"SELECT id,filmName,description,ageLimit,year,idUser FROM movies WHERE id = %s; "), 
		GET_BY_FIELD(SqlQueries.GET_BY_FIELD,"SELECT id,filmName,description,ageLimit,year,idUser FROM movies WHERE %s = '%s'; "), 
		GET_ALL(SqlQueries.GET_ALL,"SELECT id,filmName,description,ageLimit,year,idUser from movies;"), 
		UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID,"UPDATE movies SET filmName = '%s', description = '%s', ageLimit = '%s' WHERE id = %s;"), 
		UPDATE_BY_FIELD(SqlQueries.UPDATE_BY_FIELD,"UPDATE movies SET %s = '%s' WHERE %s = '%s';"), 
		DELETE_BY_ID(SqlQueries.DELETE_BY_ID,"DELETE FROM movies where id = %s;"), 
		DELETE_BY_FIELD(SqlQueries.DELETE_BY_FIELD,"DELETE FROM movies where %s = '%s'; ");
																		
		private SqlQueries sqlQueries;
		private String query;

		private MovieEntityQueries(SqlQueries sqlQueries, String query) {
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

	// id
	private String filmName;
	private String description;
	private String ageLimit;
	private Integer year;
	private Long userId;
	
	public Movie(Long id, String filmName, String description, String ageLimit, Integer year, Long userId) {
		super(id);
		this.filmName = filmName;
		this.description = description;
		this.ageLimit = ageLimit;
		this.year = year;
		this.userId = userId;
	}

	//Getter
	public String getFilmName() {
		return filmName;
	}

	public String getDescription() {
		return description;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public Integer getYear() {
		return year;
	}

	public Long getUserId() {
		return userId;
	}
	
	//Setter
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Movie [filmName=" + filmName + ", description=" + description + ", ageLimit=" + ageLimit + ", year="
				+ year + ", userId=" + userId + ", getId()=" + getId() + "]";
	}

}
