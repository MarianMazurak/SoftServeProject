package com.mazurak.cinema.dto;

public class UserDto {
	
	private Long id;
	private String name;
	private String login;
	private String password;
	private String role;

	public UserDto() {
	}

	public UserDto(Long id,String name, String login, String password,String role) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.role = role;
	}

	//Getter
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}
	
	//Setter
	public void setName(String name) {
		this.name = name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [name=" + name + ", login=" + login + ", password=" + password + ", role=" + role + "]";
	}
	
}
