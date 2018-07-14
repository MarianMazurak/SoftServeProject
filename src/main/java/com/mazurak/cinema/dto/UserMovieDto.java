package com.mazurak.cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class UserMovieDto {

	private final int DEFAULT_PAGE_OFFSET = 10;
	private String userLogin;
	private List<MovieDto> movies;
	private int pageCount;
	private int pageOffSet;
	
	public UserMovieDto() {
		this.userLogin = userLogin;
		this.movies = new ArrayList<MovieDto>();
		this.pageCount = (movies.size() / DEFAULT_PAGE_OFFSET) + 1;
		this.pageOffSet = DEFAULT_PAGE_OFFSET;
	}
	
	public UserMovieDto(String userLogin) {
		this.userLogin = userLogin;
		this.movies = new ArrayList<MovieDto>();
		this.pageCount = (movies.size() / DEFAULT_PAGE_OFFSET) + 1;
		this.pageOffSet = DEFAULT_PAGE_OFFSET;
	}
	
	public UserMovieDto(String userLogin, List<MovieDto> movies) {
		this.userLogin = userLogin;
		this.movies = movies;
		this.pageCount = (movies.size() / DEFAULT_PAGE_OFFSET) + 1;
		this.pageOffSet = DEFAULT_PAGE_OFFSET;
	}

	
	
	//Getter
	public String getUserLogin() {
		return userLogin;
	}
	
	public List<MovieDto> getMovies() {
		return movies;
	}

	public int getPageCount() {
		return pageCount;
	}
	
	public int getPageOffSet() {
		return pageOffSet;
	}
	
	
	//Setter
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setMovies(List<MovieDto> movies) {
		this.movies = movies;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPageOffSet(int pageOffSet) {
		this.pageOffSet = pageOffSet;
	}
	
	public void addMovieDto(MovieDto movieDto) {
		movies.add(movieDto);
	}
	
	@Override
	public String toString() {
		return "UserMovieDto ["  + " userLogin=" + userLogin + ", movies="
				+ movies + ", pageCount=" + pageCount + ", pageOffSet=" + pageOffSet + "]";
	}
}
