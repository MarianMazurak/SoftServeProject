package com.mazurak.cinema.controller.movie;

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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isActiveSession = true;
		HttpSession session = req.getSession(false); 
		Cookie idSessionCookie = null;
		for (Cookie currentCookie : req.getCookies()) {
			if (currentCookie.getName().equals("id_session")) {
				idSessionCookie = currentCookie;
				break;
			}
		}
		isActiveSession =
				isActiveSession && (session != null) && (session.getAttribute("loginDto") != null)
						&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null)
						&& (idSessionCookie != null);
		isActiveSession = isActiveSession && (idSessionCookie.getValue().equals(session.getId()));
		//
		if (!isActiveSession) {
			getServletConfig().getServletContext()
					.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
					.forward(req, resp);
		} else {

			req.setAttribute(AttributeName.AGE_LIMIT_ATTR, AgeLimit.values());
			req.setAttribute(AttributeName.USER_NAME,
					req.getSession().getAttribute(AttributeName.USER_NAME_ATTRIBUTE));
			req.getRequestDispatcher(ViewUrls.CREATE_MOVIES_PAGE_JSP.toString()).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		//
		String movieName = req.getParameter(ParameterName.NAME_PARAM);
		String description = req.getParameter(ParameterName.DESCRIPTION_PARAM);
		String ageLimit = req.getParameter(ParameterName.AGE_LIMIT_PARAM);
		Integer year = Integer.parseInt(req.getParameter(ParameterName.YEAR_PARAM));
		//
		Long userId =
				Long.parseLong(req.getSession().getAttribute(AttributeName.USER_ID_ATTRIBUTE).toString());
		MovieDto movieDto = new MovieDto(0L, movieName, description, ageLimit, year, userId);
		movieService.saveMovieToDataBase(movieDto);

		resp.sendRedirect(ControllerUrls.LIST_USER_MOVIES_PAGINATION.toString());
	}
}
