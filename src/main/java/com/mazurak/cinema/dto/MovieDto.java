package com.mazurak.cinema.dto;

public class MovieDto {

	private Long movieId;
	private String filmName;
	private String description;
	private String ageLimit;
	private Integer year;
	private Long userId;
	
	public MovieDto(Long movieId, String filmName, String description, String ageLimit, Integer year, Long userId) {
	this.movieId = movieId;
	this.filmName = filmName;
	this.description = description;
	this.ageLimit = ageLimit;
	this.year = year;
	this.userId = userId;
}
	
	// Getter
	public Long getMovieId() {
		return movieId;
	}

	public String getFilmName() {
		return filmName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	// Setter
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

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

}
