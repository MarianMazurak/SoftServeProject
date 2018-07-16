package com.mazurak.cinema.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mazurak.cinema.controller.common.Security;
import com.mazurak.cinema.dto.UserDto;
import com.mazurak.cinema.service.IoCContainer;
import com.mazurak.cinema.service.UserService;

@WebServlet("/updateuser/*")
public class AdminUpdateUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public AdminUpdateUserServlet() {
		this.userService = IoCContainer.getInit().getUserService();
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// check active session
		Security.isActiveSession(req, resp);

		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idUser = Long.parseLong(split[split.length - 1]);

		req.setAttribute("userRoleAttr", userService.getUserRole(idUser));
		req.getRequestDispatcher("/WEB-INF/views/admin-update-user-page.jsp").forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// check active session
		Security.isActiveSession(req, resp);
		
		String uri = req.getRequestURI().toString();
		String[] split = uri.split("/");
		Long idUser = Long.parseLong(split[split.length - 1]);

		UserDto userDto = userService.getUserDto(idUser);
		userService.setUserDto(userDto);

		String newRole = req.getParameter("newRole");


		System.out.println(req.getParameter("newRole"));
		userService.setUserDto(userDto);

	}



}
