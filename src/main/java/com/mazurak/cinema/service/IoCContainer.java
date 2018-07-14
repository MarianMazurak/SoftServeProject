package com.mazurak.cinema.service;

import com.mazurak.cinema.dao.MovieDao;
import com.mazurak.cinema.dao.RoleDao;
import com.mazurak.cinema.dao.UserDao;

public final class IoCContainer {

	private static volatile IoCContainer ioCContainer = null;
	
	private MovieDao movieDao;
	private RoleDao roleDao;
	private UserDao userDao;
	
	private MovieService movieService;
	private UserMovieService userMovieService;
	private UserService userService;
	
	private IoCContainer() {
		createInstanceDaos();
		createInstanceServices();
	}
	
	private void createInstanceDaos() {
		this.movieDao = new MovieDao();
		this.roleDao = new RoleDao();
		this.userDao = new UserDao();
	}
	
	private void createInstanceServices() {
		this.movieService = new MovieService();
		this.userMovieService = new UserMovieService();
		this.userService = new UserService();
	}
	
	public static IoCContainer  getInit() {
		if(ioCContainer == null) {
			synchronized (IoCContainer.class) {
				if(ioCContainer == null) {
					ioCContainer = new IoCContainer();
				}
			}
		}
		return ioCContainer;
	}
	
	//Getters 

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public MovieService getMovieService() {
		return movieService;
	}

	public UserMovieService getUserMovieService() {
		return userMovieService;
	}

	public UserService getUserService() {
		return userService;
	}
}
