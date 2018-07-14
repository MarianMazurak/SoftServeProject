package com.mazurak.cinema.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserMovieService;

@WebServlet("/delete/*")
public class DeleteMovieServlet extends HttpServlet {
	
	private UserMovieService userMovieService;
	
	public DeleteMovieServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idMovie = Long.parseLong(split[split.length -1]);
		userMovieService.deleteMovieById(idMovie);
		
//		Long idUser = Long.parseLong(req.getSession().getAttribute("idAttr").toString());
//		req.setAttribute("moviesAttr", userMovieService.getUserMoviesById(idUser));
		
		
		String pageNum = String.valueOf(req.getSession().getAttribute("carrentPage").toString());
		resp.sendRedirect(req.getContextPath()+"/listusersmovies?page=" + pageNum);
		
	}

	
}
