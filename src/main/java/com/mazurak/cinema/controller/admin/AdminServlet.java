package com.mazurak.cinema.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mazurak.cinema.controllers.enums.ViewUrls;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserService;

@WebServlet("/userslist")
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	public AdminServlet () {
	
		this.userService = IoCContainer.getInit().getUserService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("userAttr", userService.getAllUsers());
		req.getRequestDispatcher(ViewUrls.USER_LIST_PAGE_JSP.toString()).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

}
