package com.PSMS.Hibernate;

public class Role {
	private int id;
	private String Role_name;
	private String Role_type;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole_name() {
		return Role_name;
	}
	public void setRole_name(String role_name) {
		Role_name = role_name;
	}
	public String getRole_type() {
		return Role_type;
	}
	public void setRole_type(String role_type) {
		Role_type = role_type;
	}
	
}
