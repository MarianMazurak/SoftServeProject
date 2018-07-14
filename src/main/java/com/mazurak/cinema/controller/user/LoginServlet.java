package com.mazurak.cinema.controller.user;

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

@WebServlet({"","/login"})
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1336547045057016875L;
	private UserService userService;

	// Use IoCContainer
	public LoginServlet() {
		userService = IoCContainer.getInit().getUserService();
	}

	//
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(ViewUrls.LOGIN_PAGE_JSP.toString())
			.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		LoginDto loginDto = new LoginDto(login, password);

		if (userService.isValid(loginDto)) {
			String userName = userService.getUserNameByLoginDto(loginDto); // Excepton try ca
			Long userId = userService.getIdUserByLogin(loginDto);
			Long userRole = userService.getUserRole(loginDto);
//			String userRole = userService.getUserRole(loginDto);
			
			// Create session (must be true)
			HttpSession session = req.getSession(true);
			
			session.setMaxInactiveInterval(200_000);
			// Hold User name and id in session -> use in second page (UserPAgeServlet)
			
			session.setAttribute("nameAttr", userName);
			session.setAttribute("idAttr", userId);
			// Send user Role
			session.setAttribute("roleAttr", userRole);
			
			
			Cookie cookie = new Cookie("id_session", session.getId());
			resp.addCookie(cookie);
			resp.sendRedirect(req.getRequestURI() + ControllerUrls.USER_PAGE_SERVLET.toString());
		} else {
			req.setAttribute("error", "Bad Login or Password");
			req.getRequestDispatcher(ViewUrls.LOGIN_PAGE_JSP.toString())
			.forward(req, resp);
		}
	}
}
