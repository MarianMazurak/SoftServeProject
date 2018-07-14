package com.mazurak.cinema.controllers.enums;

public enum ControllerUrls {

	LOGIN_SERVLET("/login"),
	LOGOUT_SERVLET("/logout"),
	USER_PAGE_SERVLET("userpage"),
	CREATE_MOVIE_SERVLET("/createmovie"),
	UPDATE_MOVIE_SERVLET("/updatemovie"),
	DELETE_MOVIE_SERVLET("/deletemovie"),
	USER_MOVIE_LIST_SERVLET("/listusersmovies"),
	ADMIN_USER_LIST_SERVLET("/userslist");
	
	
	
	
//	ROOT_SERVLET("/"),
//	
	//
//								USER_CREATE_SERVLET("/usercreate"),
//								USER_EDIT_SERVLET("/useredit"),
//								USER_UPDATE_SERVLET("/userupdate"),
//								USER_CANCEL_SERVLET("/usercancel"),
//								//
//								ITEM_CREATE_SERVLET("/itemcreate"),
//								ITEM_EDIT_SERVLET("/itemedit"),
//								ITEM_UPDATE_SERVLET("/itemupdate"),
//								ITEM_CANCEL_SERVLET("/itemcancel"),
//								ITEM_DELETE_SERVLET("/itemdelete"),
//								//
//								USER_ITEMS_SERVLET("/useritems");
	//
	private String url;

	private ControllerUrls(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return url;
	}
}
