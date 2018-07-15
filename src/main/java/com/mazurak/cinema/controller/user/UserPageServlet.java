package com.mazurak.cinema.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mazurak.cinema.constant.AttributeName;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.LoginDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserMovieService;

@WebServlet("/userpage")
public class UserPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserMovieService userMovieService;

	public UserPageServlet() {
		this.userMovieService = IoCContainer.getInit().getUserMovieService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isActiveSession = true;
		HttpSession session = req.getSession(false); // Do not Create new Session
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
					.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString()).forward(req, resp);
		} else {
			Long id = Long.parseLong(req.getSession()
					.getAttribute(AttributeName.USER_ID_ATTRIBUTE).toString());

			Long roleId = Long.parseLong(req.getSession()
					.getAttribute(AttributeName.USER_ROLE_ATTRIBUTE).toString());

			try {
				req.setAttribute(AttributeName.USER_ROLE_ID, roleId);
				req.setAttribute(AttributeName.USER_ID, id);
				req.setAttribute(AttributeName.COUNT_ATTR, userMovieService.getCountMovieInUser(id));

				req.getRequestDispatcher(ViewUrls.USER_PAGE_JSP.toString()).forward(req, resp);
			} catch (RuntimeException e) {
				req.getRequestDispatcher(ViewUrls.USER_PAGE_JSP.toString()).forward(req, resp);
			}
		}
	}
}
