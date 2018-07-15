package com.mazurak.cinema.controller.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.constant.ParameterName;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.LoginDto;
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
		boolean isActiveSession = true;
		HttpSession session = req.getSession(false); 
		Cookie idSessionCookie = null;
		for (Cookie currentCookie : req.getCookies()) {
			if (currentCookie.getName().equals("id_session")) {
				idSessionCookie = currentCookie;
				break;
			}
		}
		isActiveSession = isActiveSession && (session != null)
				&& (session.getAttribute("loginDto") != null)
				&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null)
				&& (idSessionCookie != null);
		isActiveSession = isActiveSession
				&& (idSessionCookie.getValue().equals(session.getId()));
		//
		if (!isActiveSession) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(req, resp);
		} else {
		resp.setContentType("text/html;charset=UTF-8");
		Long idUser = Long.parseLong(req.getSession()
				.getAttribute(AttributeName.USER_ID_ATTRIBUTE).toString());
		Long roleId = Long.parseLong(req.getSession()
				.getAttribute(AttributeName.USER_ROLE_ATTRIBUTE).toString());

		try {
			int currentPage = 0;
			currentPage = Integer.parseInt(req.getParameter(ParameterName.PAGE)); // CHECK NULL
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
			req.getSession().setAttribute(AttributeName.CURRENT_PAGE, currentPage);
			int begin = Math.max(1, currentPage - 4);
			int end = Math.min(begin + 4, currentPage + 2);

			req.setAttribute("beginIndex", begin);
			req.setAttribute("endIndex", end);
			req.setAttribute("currentIndex", currentPage);
			req.setAttribute("numberOfAllPages", numOfPage);
			
			if (roleId == 1) {
				req.setAttribute(AttributeName.MOVIE_ATTR, userMovieService.getMoviePagination(
						userMovieService.getUserMoviesById(idUser), currentPage + 1, recordsPerPage));
					req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			} else {
				req.setAttribute(AttributeName.MOVIE_ATTR, userMovieService.getMoviePagination(userMovieService.getAllMovie(),
						currentPage + 1, recordsPerPage));
				req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
			}
		} catch (RuntimeException e) {

			req.getRequestDispatcher(ViewUrls.USER_LIST_MOVIES_JSP.toString()).forward(req, resp);
		}
	}
}
}
