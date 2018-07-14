package com.mazurak.cinema.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDto {

	private List<String> roles;

	public RoleDto(List<String> roles) {
		this.roles = roles;
	}

	public RoleDto() {
		this.roles = new ArrayList<String>();
	}

	//Setter
	public void addRole(String role) {
		roles.add(role);
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	//Getter	
	public List<String> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "RoleDto [roles=" + roles + "]";
	}
}
	