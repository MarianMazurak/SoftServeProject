package com.mazurak.cinema.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.controllers.enums.ViewUrls;
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getSession().getAttribute("idAttr").toString());
		Long roleId = Long.parseLong(req.getSession().getAttribute("roleAttr").toString());
//		String roleId = req.getSession().getAttribute("roleAtr").toString();
		System.out.println("ROLE" + roleId);
		try {
			req.setAttribute("userId", id);
			req.setAttribute("countAttr", userMovieService.getCountMovieInUser(id));
			req.setAttribute("userRoleId", roleId);
			req.getRequestDispatcher(ViewUrls.USER_PAGE_JSP.toString()).forward(req, resp);
		} catch (RuntimeException e) {
			req.getRequestDispatcher(ViewUrls.USER_PAGE_JSP.toString()).forward(req, resp);
		}
	}
}
