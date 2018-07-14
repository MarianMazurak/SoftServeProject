package com.mazurak.cinema.controller.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserMovieService;

@WebServlet("/listusersmovies")
public class UserMoviesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserMovieService userMovieService;

	public UserMoviesServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		Long idUser = Long.parseLong(req.getSession().getAttribute("idAttr").toString());
		Long roleId = Long.parseLong(req.getSession().getAttribute("roleAttr").toString());

		try {
			// Check role

			int currentPage = 0;
			currentPage = Integer.parseInt(req.getParameter("page")); // CHECK NULL
			int recordsPerPage = 10;
			int numOfRows = 0;
			if (roleId == 1) {
				numOfRows = userMovieService.getCountMovieInUser(idUser);
			} else {
				numOfRows = userMovieService.getAllMovie().size();
			}

			int numOfPage = (numOfRows / recordsPerPage);
			if (numOfPage % recordsPerPage >= 0) {
				++numOfPage;
			}
			req.getSession().setAttribute("carrentPage", currentPage);
			int begin = Math.max(1, currentPage - 4);
			int end = Math.min(begin + 4, currentPage + 2);

			req.setAttribute("beginIndex", begin);
			req.setAttribute("endIndex", end);
			req.setAttribute("currentIndex", currentPage);
			req.setAttribute("numberOfAllPages", numOfPage);
			
			if (roleId == 1) {
				req.setAttribute("moviesAttr", userMovieService.getMoviePagination(
						userMovieService.getUserMoviesById(idUser), currentPage + 1, recordsPerPage));
					req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			} else {
				req.setAttribute("moviesAttr", userMovieService.getMoviePagination(userMovieService.getAllMovie(),
						currentPage + 1, recordsPerPage));
				req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			}
		} catch (RuntimeException e) {

			req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
		}
	}
}
