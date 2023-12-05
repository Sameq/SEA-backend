package com.example.sea.model;

public enum UserRole {
	
	ALUNO("ROLE_ALUNO"), // igual a 0
	PROFESSOR("ROLE_PROFESSOR"); // igual a 1
	
	private String role;
	
	UserRole(String role){
		this.role = role;
	}
	
	public String getRole(String role) {
		return role;
	}
}
