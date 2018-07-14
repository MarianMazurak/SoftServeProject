package com.mazurak.cinema.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.MovieDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.MovieService;

@WebServlet("/updatemovie/*")
public class UpdateMovieServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private MovieService movieService;
	
	public UpdateMovieServlet() {
		this.movieService = IoCContainer.getInit().getMovieService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idMovie = Long.parseLong(split[split.length -1].toString());
		
		req.setAttribute("id", idMovie);
		req.setAttribute("movieAttr",movieService.getMovieDto(idMovie));
		req.getRequestDispatcher(ViewUrls.UPDATE_MOVIES_PAGE_JSP.toString()).forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long idUser = Long.parseLong(req.getSession().getAttribute("idAttr").toString());
		Long idMovie = Long.parseLong(req.getParameter("id"));
		String newName = req.getParameter("newnameParam");
		String newDesc = req.getParameter("newdescriptionParam");
		String newAgeLimit  = req.getParameter("newAgeLimitParam");
		Integer newYear = Integer.parseInt(req.getParameter("newyearParam"));
		
		MovieDto movieDto = new MovieDto(idMovie,newName, newDesc, newAgeLimit, newYear, idUser);
		movieService.setMovieDto(movieDto, idUser);
		String pageNum =  req.getSession().getAttribute("carrentPage").toString();
		resp.sendRedirect(req.getContextPath()+"/listusersmovies?page=" + pageNum);
		
		//resp.sendRedirect("http://localhost:8080/SoftServeProject/listusersmovies");
	}
	
}
