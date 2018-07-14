package com.mazurak.cinema.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mazurak.cinema.dao.MovieDao;
import com.mazurak.cinema.dao.UserDao;
import com.mazurak.cinema.dto.MovieDto;
import com.mazurak.cinema.dto.UserMovieDto;
import com.mazurak.cinema.entity.Movie;
import com.mazurak.cinema.entity.User;

public class UserMovieService {
	
	private UserDao userDao;
	private MovieDao movieDao;

	public UserMovieService() {
		userDao = new UserDao();
		movieDao = new MovieDao();
	}
	
	public UserMovieService(UserDao userDao, MovieDao movieDao) {
		this.userDao = userDao;
		this.movieDao = movieDao;
	}
	
	
	public UserMovieDto getUserMovies(Long idUser) {
		User user = userDao.getById(idUser);
		UserMovieDto userMovieDto = new UserMovieDto(user.getLogin());
		for(Movie movie : movieDao.getMovieEntityByIdUser(idUser)) {
			MovieDto movieDto = new MovieDto(movie.getId(),movie.getFilmName(),
					movie.getDescription(),
					movie.getAgeLimit(),
					movie.getYear(),
					movie.getUserId());
			userMovieDto.addMovieDto(movieDto);
			
		}
		return userMovieDto;
	}

	// (MovieDTO) Done
	public List<MovieDto> getUserMoviesById(Long idUser) {
		return getUserMovies(idUser).getMovies();
	}
	
	public int getCountMovieInUser(Long idUser) {
		return getUserMoviesById(idUser).size();
	}
	
	// (MovieDTO) Done 
	public List<MovieDto> getMoviePagination(List<MovieDto> sourceList, Integer pageNumer, Integer pageSize){
		if(pageSize <= 0 || pageNumer <= 0 ) {
			 throw new IllegalArgumentException("invalid page size: " + pageSize);
		}
		int fromIndex = (pageNumer -1) * pageSize;
		if(sourceList == null || sourceList.size() < fromIndex) {
			return Collections.emptyList();
		}
		return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
	}
	
	public void deleteMovieById(Long id) {
		movieDao.deleteById(id);
	}
	
	//GetAll Movies for Admin page
	public List<MovieDto> getAllMovie() {
		List<MovieDto> listMovieDto = new ArrayList<>();
		for (Movie movie : movieDao.getAll()) {
			MovieDto movieDto = new MovieDto(movie.getId(), movie.getFilmName(), movie.getDescription(),
					movie.getAgeLimit(), movie.getYear(), movie.getUserId());
			listMovieDto.add(movieDto);
		}
		return listMovieDto;
	}
	
}
