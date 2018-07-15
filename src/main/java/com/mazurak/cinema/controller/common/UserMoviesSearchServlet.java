package com.mazurak.cinema.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserMovieService;
@WebServlet("/search")
public class UserMoviesSearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserMovieService userMovieService;
	
	public UserMoviesSearchServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setAttribute("movieAttr", userMovieService
				.searchMovie(req.getParameter("searchParam")));

		req.getRequestDispatcher("/WEB-INF/views/search-page.jsp").forward(req, resp);
	}
	
}
