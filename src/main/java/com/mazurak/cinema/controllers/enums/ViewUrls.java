package com.mazurak.cinema.controllers.enums;

public enum ViewUrls {

	LOGIN_PAGE_JSP("/WEB-INF/views/login-page.jsp"),
	REGISTER_PAGE_JSP("/WEB-INF/views/registration-page.jsp"),
	USER_PAGE_JSP("/WEB-INF/views/user-page.jsp"),
	USER_LIST_MOVIES_JSP("/WEB-INF/views/list-movies.jsp"),
	CREATE_MOVIES_PAGE_JSP("/WEB-INF/views/create-movie-page.jsp"),
	UPDATE_MOVIES_PAGE_JSP("/WEB-INF/views/update-page.jsp"),
	USER_LIST_PAGE_JSP("/WEB-INF/views/user-list-page.jsp");
	
	private String url;

	private ViewUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
