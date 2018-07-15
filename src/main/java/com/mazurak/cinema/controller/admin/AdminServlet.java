package com.mazurak.cinema.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mazurak.cinema.controllers.enums.ControllerUrls;
import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.dto.LoginDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserService;

@WebServlet("/userslist")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public AdminServlet() {

		this.userService = IoCContainer.getInit().getUserService();
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
					.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
					.forward(req, resp);
		} else {
			req.setAttribute("userAttr", userService.getAllUsers());
			req.getRequestDispatcher(ViewUrls.USER_LIST_PAGE_JSP.toString()).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
