package com.mazurak.cinema.dto;

public class LoginDto {
	
	private String login;
	private String password;

	public LoginDto(String login, String password) {
		this.login = login;
		this.password = password;
	}

	//Getter
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	//Setter
	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [login=" + login + ", password=" + password + "]";
	}
	
}
