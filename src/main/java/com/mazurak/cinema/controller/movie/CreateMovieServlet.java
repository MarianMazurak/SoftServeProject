package com.mazurak.cinema.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.MovieDto;
import com.mazurak.cinema.entity.enums.AgeLimit;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.MovieService;

@WebServlet("/createmovie")
public class CreateMovieServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private MovieService movieService;

	public CreateMovieServlet() {
		this.movieService = IoCContainer.getInit().getMovieService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("ageLimitAttr", AgeLimit.values());
		req.setAttribute("userNameAttr", req.getSession().getAttribute("nameAttr"));
		req.getRequestDispatcher(ViewUrls.CREATE_MOVIES_PAGE_JSP.toString()).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//
		String movieName = req.getParameter("nameParam");
		String description = req.getParameter("descriptionParam");
		String ageLimit = req.getParameter("ageLimitParam");
		Integer year = Integer.parseInt(req.getParameter("yearParam"));
		//
		Long userId = Long.parseLong(req.getSession().getAttribute("idAttr").toString());
		MovieDto movieDto = new MovieDto(0L, movieName, description, ageLimit, year, userId);
		movieService.saveMovieToDataBase(movieDto);

		// TODO Better
		resp.sendRedirect("http://localhost:8080/SoftServeProject/listusersmovies?page=0");
	}

}
